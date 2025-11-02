package studio.noololly.cftoggle.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class TerminalCommunicator {

    private static class StreamGobbler implements Runnable {
        private final InputStream inputStream;
        private final Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }

    private int runTerminal() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        String homeDirectory = System.getProperty("user.home");
        String command = String.format("/bin/sh -c ls %s", homeDirectory);

        Process process = Runtime.getRuntime().exec(command);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<?> outFuture = executor.submit(new StreamGobbler(process.getInputStream(), System.out::println));
            Future<?> errFuture = executor.submit(new StreamGobbler(process.getInputStream(), System.err::println));

            int exitCode = process.waitFor();

            outFuture.get(10, TimeUnit.SECONDS);
            errFuture.get(10, TimeUnit.SECONDS);

            return exitCode;
        } finally {
            executor.shutdownNow();
            process.destroy();
        }
    }

    public TerminalCommunicator() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        runTerminal();
    }
}
