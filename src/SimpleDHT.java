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

    public final static String HELP = "For the first node in a p2p network: (It gets ID 1 and others incremented.)

    java SimpleDHT <HOSTNAME> <PORT> F
    i.e java SimpleDHT localhost 9000 F

For other peers:
(First two parameters new peer's information. Last three known peer in network.)

    java SimpleDHT <HOSTNAME> <PORT> <ID> <HOSTNAME> <PORT>
    i.e java SimpleDHT localhost 9001 1 localhost 9000

Communication with peers as its below:
(All activity logged in p2p.log file directory at where peer runs.)

    telnet <HOSTNAME> <PORT>
    i.e telnet ubuntu 2112

These are commands that can be sent to peer: (Operations)

    ADD — Add a string to be stored at the peer. i.e ADD 3171a 3/1.0 1CRLFwhat time is it?CRLF
    QUERY — Determine whether or not this peer is storing a specific string. i.e QUERY 3171a 3/1.0 1CRLFkumquatCRL
    RETRIEVE -
    REMOVE -

Format for messages: (Version for protocol = SDHT_1.0 )

    <OPERATION> <space> <VERSION> <space> <NumberOfFollowingLines> <space> <EOL> 0 or more following lines ending with <EOL>
    i.e ADD SDHT_1.0 1 EOL foo bar baz qux quux EOL

Format for response:

    <VERSION> <space> <OPERATION> <space> <NumberOfFollowingLines> <space> <RESPONSE_CODE> <space> <RESPONSE_MESSAGE> <EOL>
    0 or more following lines ending with <EOL>
    i.e SDHT_1.0 ADD 1 200 OK EOL foo bar baz qux quux EOL\n";

    /**
     * Processes command line arguments.
     * @param commands
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
