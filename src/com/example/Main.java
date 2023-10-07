package com.example;

import java.io.IOException;

public class Main {
	public static void main(String [] args) throws IOException {
		boolean arg_server = false, arg_client = false;
		String transport = null;
		int port = 0;

		// parse args
		for (int i = 0; i < args.length ; i++) {
			String arg = args[i];
			if(arg.startsWith("-")) {
				switch (arg) {
					case "-p":
						port = Integer.parseInt(args[i+1]);
					case "-s":
						arg_server = true;
					case "-c":
						arg_client = true;
					case "-t":
						transport = args[i+1];
					}
				}
			}

		if (arg_server) {
			if (transport.equals("tcp")) {
				Logger.logInfo("starting tcp server at port " + port);
				ServerTCP server = new ServerTCP(port);
				server.start();
				server.stop();
				System.exit(0);

			}	
			if (transport.equals("udp")) {
				System.exit(0);

			}

			System.exit(1);

		}

		if (arg_client) {
			if (transport.equals("tcp")) {

			}	
			if (transport.equals("udp")) {
				
			}
		}
	}
}
