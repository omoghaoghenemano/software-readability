package de.uni_passau.fim.se2.sa.readability;

import de.uni_passau.fim.se2.sa.readability.subcommands.SubcommandClassify;
import de.uni_passau.fim.se2.sa.readability.subcommands.SubcommandPreprocess;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "subcommands", subcommands = {SubcommandPreprocess.class, SubcommandClassify.class})
public class ReadabilityAnalysisMain implements Callable<Integer> {


    public static void main(String[] args) {
        int exitCode = new CommandLine(new ReadabilityAnalysisMain()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        System.out.println("Subcommand needed: 'preprocess' or 'classify'");
        return 0;
    }


}