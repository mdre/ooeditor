/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomecontrols.ooeditor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class OOConfigTest {
    
    private static final Logger LOGGER = Logger.getLogger(OOConfigTest.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.FINEST);
        }
    }
    
    String storageServerIP = "http://localhost:9000";
    
    public OOConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
//    @Test
    public void runCode() {
        LOGGER.log(Level.FINEST, "ForceSave detectado");
        try {
            JSONObject fs = new JSONObject();
            fs.put("c", "forcesave");
            fs.put("key", "OG5YNUdJWUdIRVVEUC81QStqTlFhcjZMUVFMaTk2TFdlSjJ0Z0crZDU4algrWDdFeHp5NzBSWHBoOHhrY3RoNQ==");
                    
            LOGGER.log(Level.FINEST, "post command: "+fs.toString(4));
            
            org.apache.http.HttpResponse response = Request.Post("http://172.16.2.88/coauthoring/CommandService.ashx")
                    .bodyString(fs.toString(), ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            
            LOGGER.log(Level.FINEST, "res: "+response.getStatusLine());
            LOGGER.log(Level.FINEST, "res: "+EntityUtils.toString(response.getEntity()));
        } catch (IOException ex) {
            Logger.getLogger(OOConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    /**
     * Test of withDocumentFilename method, of class OOConfig.
     */
    @Test
    public void testWithDocumentFilename() {
        try {
            System.out.println("withDocumentFilename");
            String fileNameWithPath = "/testOnlyOffice.docx";
            OOConfig instance = new OOConfig(new URL(storageServerIP))
                                        .setDocumentConfig().withFilename(fileNameWithPath)
                                                            .withType("docx")
                                                            .withTitle("testOnlyOffice.docx")
                                                            .withPermissions(
                                                                    OODocumentPermission.enableComment,
                                                                    OODocumentPermission.enableCopy,
                                                                    OODocumentPermission.enableEdit
                                                                    )
                                                            .build()
                                        ;
            
            System.out.println("toString: "+instance.toString());
            // TODO review the generated test code and remove the default call to fail.
        } catch (MalformedURLException ex) {
            Logger.getLogger(OOConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of withDocumentType method, of class OOConfig.
     */
//    @Test
    public void testWithDocumentType() {
        System.out.println("withDocumentType");
        String type = "";
        OOConfig instance = null;
        OOConfig expResult = null;
//        OOConfig result = instance.withDocumentType(type);
//        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of withDocumentTitle method, of class OOConfig.
     */
//    @Test
    public void testWithDocumentTitle() {
        System.out.println("withDocumentTitle");
        String title = "";
        OOConfig instance = null;
//        OOConfig expResult = null;
//        OOConfig result = instance.withDocumentTitle(title);
//        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class OOConfig.
     */
//    @Test
    public void testToString() {
        System.out.println("toString");
        OOConfig instance = null;
        String expResult = "";
        String result = instance.toString();
        Assert.assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        Assert.fail("The test case is a prototype.");
    }
    
}
