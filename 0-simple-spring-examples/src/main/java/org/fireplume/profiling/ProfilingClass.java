package org.fireplume.profiling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProfilingClass {

    /**
     * method will be profiled if it marked with this annotation
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD})
    public @interface CustomProfiling {
        boolean isWriteToFile() default false;

        /**
         * without <code>{methodName}</code> mark, method name wont be present in mark
         *
         * @return mark string
         */
        String logMark() default "*** Profiling {methodName} ***";

        String methodNameMarkInLogMark() default "{methodName}";

        String filePath() default "";

        String fileName() default "prof.res";
    }

    /**
     *
     */
    @Component
    public class ProfilingBeanPostProcessor implements BeanPostProcessor {
        private final Logger logger = LoggerFactory.getLogger(ProfilingClass.ProfilingBeanPostProcessor.class);
        private final Map<String, ProxyInfo> map = new HashMap<>();

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            // Get bean class
            final Class<?> beanClass = bean.getClass();
            // lookup through all methods
            for (Method declaredMethod : beanClass.getDeclaredMethods()) {
                // if method has profiling annotation then remember this bean and method
                final CustomProfiling annotation = declaredMethod.getDeclaredAnnotation(CustomProfiling.class);
                if (annotation != null) {
                    // remembering...
                    map.compute(beanName, (s, proxyInfo) -> {
                        proxyInfo = proxyInfo == null ?
                                new ProxyInfo(
                                        beanName,
                                        bean.getClass(),
                                        new ArrayList<>(),
                                        annotation.isWriteToFile(),
                                        annotation.filePath(),
                                        annotation.fileName(),
                                        annotation.logMark(),
                                        annotation.methodNameMarkInLogMark()
                                ) :
                                proxyInfo;
                        proxyInfo.getMethods().add(declaredMethod.getName());
                        return proxyInfo;
                    });

                }
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            // if map has information by bean name, then atleast one method annotated with profiling annotation
            final ProxyInfo proxyInfo = map.get(beanName);
            // but lets check it twice
            if (proxyInfo != null &&
                    Objects.equals(proxyInfo.getBeanName(), beanName) &&
                    proxyInfo.getMethods() != null &&
                    !proxyInfo.getMethods().isEmpty()) {
                // creating proxy on class without interfaces
                return Enhancer.create(proxyInfo.getBeanClass(), proxyInfo.getBeanClass().getInterfaces(),
                        (MethodInterceptor) (o, method, args, methodProxy) -> {
                            // if method is in list, than it has profiling annotation
                            if (proxyInfo.getMethods().contains(method.getName())) {
                                return callMethodWithProfiling(proxyInfo, bean, method, args, methodProxy);
                            }
                            // No modifications around method if it's without annotation
                            return methodProxy.invoke(bean, args);
                        });

//            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
//                    (proxy, method, args) -> {
//                        return method.invoke(bean, args);
//                    });
            }
            return bean;
        }

        private Object callMethodWithProfiling(ProxyInfo proxyInfo, Object bean,
                                               Method method, Object[] args, MethodProxy methodProxy)
                throws Throwable {
            // where to log: file or console
            PrintStream printStream = null;
            try {
                if (proxyInfo.isToFile()) {
                    final File file = new File(proxyInfo.getFilePath());
                    file.createNewFile();
                    printStream = new PrintStream(new FileOutputStream(file, true));
                } else {
                    printStream = System.out;

//                    printStream = new PrintStream(
//                            new LogOutputStream(
//                                    LoggerFactory.getLogger(ProfilingClass.ProfilingBeanPostProcessor.class),
//                                    Level.DEBUG
//                            )
//                    );
                }

                // setting marks
                printStream.printf("%s%n",
                        proxyInfo.getLogMark().replace(proxyInfo.methodNameMarkInLogMark, method.getName()));
                printStream.printf("Arguments: %s%n", Stream.of(args).map(Object::toString).collect(Collectors.joining(", ")));
                final long start = System.nanoTime();
                printStream.printf("Start time: %d nanos%n", start);

                // calling real (may be another proxy) method
                final Object retVal = methodProxy.invoke(bean, args);

                final long end = System.nanoTime();
                printStream.printf("End time: %d nanos%n", end);
                final long delta = end - start;
                printStream.printf("Work time: %d nanos, %f millis, %f sec%n",
                        delta, delta / 1e6, delta / 1e9);
                return retVal;
            } finally {
                if (printStream != null && printStream != System.out) {
                    printStream.close();
                }
            }
        }

        public class LogOutputStream extends OutputStream {
            /**
             * The logger where to log the written bytes.
             */
            private Logger logger;

            /**
             * The level.
             */
            private Level level;

            /**
             * The internal memory for the written bytes.
             */
            private String mem;

            /**
             * Creates a new log output stream which logs bytes to the specified logger with the specified
             * level.
             *
             * @param logger the logger where to log the written bytes
             * @param level  the level
             */
            public LogOutputStream(Logger logger, Level level) {
                setLogger(logger);
                setLevel(level);
                mem = "";
            }

            /**
             * Sets the logger where to log the bytes.
             *
             * @param logger the logger
             */
            public void setLogger(Logger logger) {
                this.logger = logger;
            }

            /**
             * Returns the logger.
             *
             * @return DOCUMENT ME!
             */
            public Logger getLogger() {
                return logger;
            }

            /**
             * Sets the logging level.
             *
             * @param level DOCUMENT ME!
             */
            public void setLevel(Level level) {
                this.level = level;
            }

            /**
             * Returns the logging level.
             *
             * @return DOCUMENT ME!
             */
            public Level getLevel() {
                return level;
            }

            /**
             * Writes a byte to the output stream. This method flushes automatically at the end of a line.
             *
             * @param b DOCUMENT ME!
             */
            public void write(int b) {
                byte[] bytes = new byte[1];
                bytes[0] = (byte) (b & 0xff);
                mem = mem + new String(bytes);

                if (mem.endsWith("\n")) {
                    mem = mem.substring(0, mem.length() - 1);
                    flush();
                }
            }

            /**
             * Flushes the output stream.
             */
            public void flush() {
                switch (level) {
                    case TRACE:
                        logger.trace(mem);
                        break;
                    case INFO:
                        logger.info(mem);
                        break;
                    case DEBUG:
                        logger.info(mem);
                        break;
                    case WARN:
                        logger.warn(mem);
                        break;
                    case ERROR:
                        logger.error(mem);
                        break;
                    default:
                        logger.info(mem);
                        break;
                }
                mem = "";
            }
        }

        private class ProxyInfo {
            private String beanName;
            private Class beanClass;
            private List<String> methods;
            private boolean isToFile;
            private String filePath;
            private String logMark;
            private String methodNameMarkInLogMark;

            public ProxyInfo(String beanName,
                             Class beanClass,
                             List<String> methods,
                             boolean isToFile,
                             String path,
                             String fileName,
                             String logMark,
                             String methodNameMarkInLogMark) {
                this.beanName = beanName;
                this.beanClass = beanClass;
                this.methods = methods;
                this.isToFile = isToFile;
                this.filePath = (path.endsWith("/") ? path : path + "/") +
                        fileName.substring(0, fileName.lastIndexOf('.')) +
                        "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd-HH-mm-ss-SSSS")) + "" +
                        fileName.substring(fileName.lastIndexOf('.'));
                this.logMark = logMark;
                this.methodNameMarkInLogMark = methodNameMarkInLogMark;
            }

            public String getBeanName() {
                return beanName;
            }

            public Class getBeanClass() {
                return beanClass;
            }

            public List<String> getMethods() {
                return methods;
            }

            public boolean isToFile() {
                return isToFile;
            }

            public String getFilePath() {
                return filePath;
            }

            public String getLogMark() {
                return logMark;
            }

            public String getMethodNameMarkInLogMark() {
                return methodNameMarkInLogMark;
            }

            public void setMethodNameMarkInLogMark(String methodNameMarkInLogMark) {
                this.methodNameMarkInLogMark = methodNameMarkInLogMark;
            }
        }
    }
}
