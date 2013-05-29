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
 * Wrapper object for response messages.
 */

public class Response {
    public String version;
    public String operation;
    public int numOfLines;
    public int responseCode;
    public String responseCodeMsg;
    public ArrayList<String> message;

    public Response(String version, String operation, int numOfLines,
                    int responseCode, String responseCodeMsg, ArrayList<String> responseMessage){
        this.version = version;
        this.operation = operation;
        this.numOfLines = numOfLines;
        this.responseCode = responseCode;
        this.responseCodeMsg = responseCodeMsg;
        this.message = responseMessage;
    }

    public String toString()
    {
        if(message.size() > 0)
        {
            String msg = "";
            for(String str : message){
                msg += str + " $";
            }
            return version +" "+ operation +" "+ numOfLines +" "+ responseCode +" "+ responseCodeMsg +" $" + msg;
        }
        else{
            return version +" "+ operation +" "+ numOfLines +" "+ responseCode +" "+ responseCodeMsg +" $";
        }
    }
}
