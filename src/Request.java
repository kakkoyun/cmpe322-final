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

import java.util.ArrayList;

/**
 * Wrapper object for request messages.
 */

public class Request {
    public String operation;
    public String version;
    public int numOfLines;
    public int peerId;
    public ArrayList<String> message;

    public Request(String operation, String version, int numOfLines) {
        this.operation = operation;
        this.version = version;
        this.numOfLines = numOfLines;
        this.message = new ArrayList<String>();
    }
    public Request(String operation, String version, int numOfLines, int peerId){
        this.operation = operation;
        this.version = version;
        this.numOfLines = numOfLines;
        this.peerId = peerId;
        this.message = new ArrayList<String>();
    }
    public Request(String operation, String version, int numOfLines, ArrayList<String> responseMessage){
        this.operation = operation;
        this.version = version;
        this.numOfLines = numOfLines;
        this.message = responseMessage;
    }
    public Request(String operation, String version, int numOfLines, int peerId, ArrayList<String> responseMessage){
        this.operation = operation;
        this.version = version;
        this.numOfLines = numOfLines;
        this.peerId = peerId;
        this.message = responseMessage;
    }

    public Request() {}

    public String toString()
    {
        if(message.size() > 0)
        {
            String msg = "";
            for(String str : message){
                msg += str + " $";
            }
            if(peerId > 0)
                return operation +" "+ version +" "+ numOfLines +" "+ peerId + " $" + msg;
            else
                return operation +" "+ version +" "+ numOfLines + " $" + msg;
        }
        else if(peerId == 0)
        {
            return operation +" "+ version +" "+ numOfLines +" $";
        }
        return operation +" "+ version +" "+ numOfLines +" "+ peerId +" $";
    }
}
