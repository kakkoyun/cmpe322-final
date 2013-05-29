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

public class Utility {

    public static int parseInt(String s){
        return Integer.parseInt(s);
    }

    /**
     * getKey : A method to calculate and integer value for given string.
     * @param value A value to be stored in hash table.
     * @return An integer value to be processed as key.
     */
    public static int getKey(String value){
        int key = 0;
        for (int i=0; i < value.length(); i++)
            key += value.charAt(i);
        return key % Settings.getInstance().Modulo;
    }

}
