/*************************************************************************
 *                                                                       *
 *  EJBCA: The OpenSource Certificate Authority                          *
 *                                                                       *
 *  This software is free software; you can redistribute it and/or       *
 *  modify it under the terms of the GNU Lesser General Public           *
 *  License as published by the Free Software Foundation; either         *
 *  version 2.1 of the License, or any later version.                    *
 *                                                                       *
 *  See terms of license at gnu.org.                                     *
 *                                                                       *
 *************************************************************************/
package org.ejbca.core.protocol.ws;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;

import org.ejbca.core.model.authorization.AccessRulesConstants;
import org.ejbca.core.protocol.ws.client.gen.EjbcaWSService;
import org.ejbca.util.CryptoProviderTools;
import org.jboss.logging.Logger;

/**
 * To run you must have the file tmp/bin/junit/jndi.properties
 * 
 * @version $Id$
 */
public class EjbcaWSTest extends CommonEjbcaWS {

    private static final Logger log = Logger.getLogger(EjbcaWSTest.class);
    
    private void setUpAdmin() throws Exception {
        super.setUp();
        CryptoProviderTools.installBCProvider();
        if (new File("p12/wstest.jks").exists()) {
            String urlstr = "https://" + hostname + ":" + httpsPort + "/ejbca/ejbcaws/ejbcaws?wsdl";
            log.info("Contacting webservice at " + urlstr);

            System.setProperty("javax.net.ssl.trustStore", "p12/wstest.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "foo123");
            System.setProperty("javax.net.ssl.keyStore", "p12/wstest.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "foo123");

            QName qname = new QName("http://ws.protocol.core.ejbca.org/", "EjbcaWSService");
            EjbcaWSService service = new EjbcaWSService(new URL(urlstr), qname);
            super.ejbcaraws = service.getEjbcaWSPort();
        }
    }
    
    

    public void test00SetupAccessRights() throws Exception {
        super.setupAccessRights();
    }
    
    public void test01EditUser() throws Exception {
    	setUpAdmin();
    	super.editUser();
    }


    public void test02FindUser() throws Exception {
        setUpAdmin();
        findUser();
    }

    public void test03_1GeneratePkcs10() throws Exception {
        setUpAdmin();
        generatePkcs10();
    }

    public void test03_2GenerateCrmf() throws Exception {
        setUpAdmin();
        generateCrmf();
    }

    public void test03_3GenerateSpkac() throws Exception {
        setUpAdmin();
        generateSpkac();
    }

    public void test03_4GeneratePkcs10Request() throws Exception {
        setUpAdmin();
        generatePkcs10Request();
    }

    public void test03_5CertificateRequest() throws Exception {
        setUpAdmin();
        certificateRequest();
    }

    public void test03_6EnforcementOfUniquePublicKeys() throws Exception {
        setUpAdmin();
        enforcementOfUniquePublicKeys();
    }

    public void test03_6EnforcementOfUniqueSubjectDN() throws Exception {
        setUpAdmin();
        enforcementOfUniqueSubjectDN();
    }

    public void test04GeneratePkcs12() throws Exception {
        setUpAdmin();
        generatePkcs12();
    }

    public void test05FindCerts() throws Exception {
        setUpAdmin();
        findCerts();
    }

    public void test06RevokeCert() throws Exception {
        setUpAdmin();
        revokeCert();
    }

    public void test07RevokeToken() throws Exception {
        setUpAdmin();
        revokeToken();
    }

    public void test08CheckRevokeStatus() throws Exception {
        setUpAdmin();
        checkRevokeStatus();
    }

    public void test09Utf8() throws Exception {
        setUpAdmin();
        utf8();
    }

    public void test10RevokeUser() throws Exception {
        setUpAdmin();
        revokeUser();
    }

    public void test11IsAuthorized() throws Exception {
        setUpAdmin();

        // This is a superadmin keystore, improve in the future
        assertTrue(ejbcaraws.isAuthorized(AccessRulesConstants.ROLE_SUPERADMINISTRATOR));
    }

    public void test13genTokenCertificates() throws Exception {
        setUpAdmin();
        genTokenCertificates( false);
    }

    public void test14getExistsHardToken() throws Exception {
        setUpAdmin();
        getExistsHardToken();
    }

    public void test15getHardTokenData() throws Exception {
        setUpAdmin();
        getHardTokenData("12345678", false);
    }

    public void test16getHardTokenDatas() throws Exception {
        setUpAdmin();
        getHardTokenDatas();
    }

    public void test17CustomLog() throws Exception {
        setUpAdmin();
        customLog();
    }

    public void test18GetCertificate() throws Exception {
        setUpAdmin();
        getCertificate();
    }

    public void test19RevocationApprovals() throws Exception {
        setUpAdmin();
        revocationApprovals();
    }

    public void test20KeyRecoverNewest() throws Exception {
        setUpAdmin();
        keyRecover();
    }

    public void test21GetAvailableCAs() throws Exception {
        setUpAdmin();
        getAvailableCAs();
    }

    public void test22GetAuthorizedEndEntityProfiles() throws Exception {
        setUpAdmin();
        getAuthorizedEndEntityProfiles();
    }

    public void test23GetAvailableCertificateProfiles() throws Exception {
        setUpAdmin();
        getAvailableCertificateProfiles();
    }

    public void test24GetAvailableCAsInProfile() throws Exception {
        setUpAdmin();
        getAvailableCAsInProfile();
    }

    public void test25GreateCRL() throws Exception {
        setUpAdmin();
        createCRL();
    }

    public void test26CvcRequest() throws Exception {
        setUpAdmin();
        cvcRequest();
    }

    public void test27EjbcaVersion() throws Exception {
        setUpAdmin();
        ejbcaVersion();
    }

    public void test28GetLastCertChain() throws Exception {
        setUpAdmin();
        getLastCertChain();
    }

    public void test29ErrorOnEditUser() throws Exception {
        setUpAdmin();
        errorOnEditUser();
    }

    public void test30ErrorOnGeneratePkcs10() throws Exception {
        setUpAdmin();
        errorOnGeneratePkcs10();
    }

    public void test31ErrorOnGeneratePkcs12() throws Exception {
        setUpAdmin();
        errorOnGeneratePkcs12();
    }

    public void test32OperationOnNonexistingCA() throws Exception {
        setUpAdmin();
        operationOnNonexistingCA();
    }

    public void test33CheckQueueLength() throws Exception {
        setUpAdmin();
        checkQueueLength();
    }

    public void test34CaRenewCertRequest() throws Exception {
        setUpAdmin();
        super.caRenewCertRequest();
    }

    public void test35CleanUpCACertRequest() throws Exception {
        setUpAdmin();
        super.cleanUpCACertRequest();
    }
    
    public void test99cleanUpAdmins() throws Exception {
        super.cleanUpAdmins();
    }


}
