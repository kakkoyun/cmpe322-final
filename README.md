Simple DHT Protocol Implementation Basic p2p Structure.
===============================================================================

Authors
-----------------
Gizem Gur			- 10976016
Kemal Akkoyun - 11076004

Summary
-----------------

Final free project for COMP322. It is a very simple implemention of Distributed
Hash Table over a p2p network. Its designed inspired from well-known Chord and
Kademlia architecture but it is very simplified version of it.
It is implemented in Java.
For further information: Kemal Akkoyun <kakkoyun@gmail.com>
												 Gizem Gur <grgizem@gmail.com>

Usage
-----------------

SimpleDHT.java is entry point for project. Works as below:

For the first node in a p2p network: (It gets ID 1 and others incremented.)

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

	ADD — Adds a string value to DHT.
	QUERY — Finds key of value in DHT.
	RETRIEVE - Retrieves value from connected node with given key.
	REMOVE - Removes given key and value pair from DHT.

Format for messages: (Version for protocol = SDHT_1.0 )

	<OPERATION> <space> <VERSION> <space> <NumberOfFollowingLines> <space> <EOL> 0 or more following lines ending with <EOL>
	i.e ADD SDHT_1.0 1 EOL foo bar baz qux quux EOL

Format for response:

	<VERSION> <space> <OPERATION> <space> <NumberOfFollowingLines> <space> <RESPONSE_CODE> <space> <RESPONSE_MESSAGE> <EOL>
	0 or more following lines ending with <EOL>
	i.e SDHT_1.0 ADD 1 200 OK EOL foo bar baz qux quux EOL
