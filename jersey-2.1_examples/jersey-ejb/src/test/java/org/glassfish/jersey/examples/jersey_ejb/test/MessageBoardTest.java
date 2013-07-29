/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012-2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.jersey.examples.jersey_ejb.test;

import java.net.URI;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.examples.jersey_ejb.resources.MyApplication;

import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Basic test for adding/removing messages.
 *
 * Tests currently disabled in pom.xml file.
 *
 * To test the app, mvn clean package and asadmin deploy target/jersey-ejb
 * and then run the tests using extenrnal test container factory:
 * mvn -DskipTests=false test
 *
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */
public class MessageBoardTest extends JerseyTest {

    final String BaseUri = "http://localhost:8080/jersey-ejb/";

    @Override
    protected Application configure() {
        return new MyApplication();
    }

    @Test
    public void testAddMessage() {
        final WebTarget webTarget = client().target(BaseUri);

        Response response = webTarget.path("app/messages").request(MediaType.TEXT_PLAIN)
                                    .post(Entity.entity("hello world!", MediaType.TEXT_PLAIN));

        assertEquals("Response status should be CREATED. Current value is \"" + response.getStatus() + "\"",
                Response.Status.CREATED.getStatusCode(), response.getStatus());



        client().target(response.getLocation()).request().delete(); // remove added message
    }

    @Test
    public void testDeleteMessage() {

        final WebTarget webTarget = client().target(BaseUri);

        URI u = null;

        Response response = webTarget.path("app/messages").request().post(Entity.entity("toDelete", MediaType.TEXT_PLAIN));
        if(response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            u = response.getLocation();
        } else {
            fail();
        }

        String s = client().target(u).request().get(String.class);

        assertTrue(s.contains("toDelete"));

        Response firstDeleteResponse = client().target(u).request().delete();
        final int successfulDeleteResponseStatus = firstDeleteResponse.getStatus();
        assertTrue("First DELETE request should return with a 2xx status code", (200 <= successfulDeleteResponseStatus) && (successfulDeleteResponseStatus < 300));

        Response nonExistentGetResponse = client().target(u).request().get();
        assertEquals("GET request to a non existent resource should return 404", 404, nonExistentGetResponse.getStatus());

        Response nonExistentDeleteResponse = client().target(u).request().delete();
        assertEquals("DELETE request to a non existent resource should return 404", 404, nonExistentDeleteResponse.getStatus());
    }

}
