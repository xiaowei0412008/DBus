/*-
 * <<
 * DBus
 * ==
 * Copyright (C) 2016 - 2017 Bridata
 * ==
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * >>
 */

package com.creditease.dbus.ws.service.schema;

import com.creditease.dbus.ws.domain.DataSchema;
import com.creditease.dbus.ws.domain.DbusDataSource;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.ArrayList;
import java.util.List;

public class MongoSchemaFetcher{
    private DbusDataSource ds;
    public MongoSchemaFetcher(DbusDataSource ds) {
        this.ds = ds;
    }


    public List<DataSchema> fetchSchema() {
        MongoClientURI uri = new MongoClientURI(ds.getMasterURL());
        MongoClient client = new MongoClient(uri);

        List<DataSchema> list = new ArrayList<>();
        DataSchema schema;

        for (String name : client.listDatabaseNames()) {
            schema = new DataSchema();
            schema.setSchemaName(name);
            schema.setStatus("active");
            list.add(schema);
        }

        client.close();

        return list;
    }

    public static void main(String[] args) {

    }
}
