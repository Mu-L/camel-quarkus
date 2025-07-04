/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.component.python.it;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PythonTest {

    @Test
    void pythonHello() {
        RestAssured.given()
                .body("Will Smith")
                .post("/python/hello")
                .then()
                .statusCode(200)
                .body(CoreMatchers.is("Hello Will Smith from Python!"));
    }

    @Test
    void pythonHigh() {
        RestAssured.given()
                .body("45")
                .post("/python/predicate")
                .then()
                .statusCode(200)
                .body(CoreMatchers.is("High"));
    }

    @Test
    void pythonLow() {
        RestAssured.given()
                .body("13")
                .post("/python/predicate")
                .then()
                .statusCode(200)
                .body(CoreMatchers.is("Low"));
    }

    @Test
    void script() {
        RestAssured.given()
                .contentType(ContentType.TEXT)
                .body(32767)
                .post("/python/route/scriptPython")
                .then()
                .statusCode(200)
                .body(Matchers.is("Hello 32767 from Python!"));
    }
}
