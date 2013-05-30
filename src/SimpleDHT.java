/*
    Created by Gizem Gür and Kemal Akkoyun on 5/29/13.

    Copyright (c) 2013 Gizem Gür. All rights reserved.
    Copyright (c) 2013 Kemal Akkoyun. All rights reserved.

    This file is part of Simple DHT.
    Simple DHT is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Simple DHT is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Simple DHT.  If not, see <http://www.gnu.org/licenses/>.

*/

import java.io.IOException;
import java.net.ServerSocket;

public class SimpleDHT {

    public final static String HELP = "For usage of program please see README.md !";

    /**
     * InitializeNode : Processes command line arguments and intiliaze peer node.
     * @param commands Command line arguments.
     * @return A peer node.
     */
    public static Node InitializeNode(String[] commands)
    {
        try{
            if(commands[2].trim().toUpperCase().equals("F")){
                return new Node(commands[0], Integer.parseInt(commands[1]));
            } else {
                return new Node(commands[0], Integer.parseInt(commands[1]),
                        Integer.parseInt(commands[2]),
                        commands[3], Integer.parseInt(commands[4]));
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Initialization error!");
            System.out.println(HELP);
            System.exit(0);
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length > 0)
        {
            ServerSocket serviceSocket = null;
            // Start node.
            Node peer = InitializeNode(args);
            peer.run();

            try {

                serviceSocket = new ServerSocket(peer.port);
                System.out.println("Service for " + peer.port + " started!");
                // Start Peer listening service.
                while (true) {
                    new Service(serviceSocket.accept(), peer).start();
                }
            } catch (IOException e) {

                System.err.println("Error while listening port: " + peer.port);
                if(serviceSocket != null){
                    serviceSocket.close();
                }
                System.exit(-1);
            }
        }
        else
        {
            System.out.println(HELP);
            System.exit(0);
        }
    }
}
