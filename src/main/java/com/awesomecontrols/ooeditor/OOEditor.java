package com.awesomecontrols.ooeditor;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@Tag("oo-editor")
@JsModule("./ooeditor/oo-editor.js")
public class OOEditor extends PolymerTemplate<IOOWrapperModel> implements  HasSize, HasTheme, HasStyle, HasComponents {
    private static final Logger LOGGER = Logger.getLogger(OOEditor.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.FINEST);
        }
    }
    
    @Id("editor")
    Div ooeditor;
    
    private URL editorServer;
    private URL commandService; 
    private String config;
    
    public OOEditor(URL docEditorServer, String config) {
        super();
        this.editorServer = docEditorServer;
        this.config = config;
        init();
//        UI.getCurrent().getPage().addJavaScript("http://192.168.88.29/web-apps/apps/api/documents/api.js");
//        UI.getCurrent().getPage().addJavaScript("http://172.16.2.88/web-apps/apps/api/documents/api.js");
        UI.getCurrent().getPage().addJavaScript(docEditorServer.toString());
        getElement().callJsFunction("initComponent",this.config);
    }
    
    public OOEditor(URL docEditorServer, OOConfig config) {
        super();
        this.editorServer = docEditorServer;
        init();
        
//        UI.getCurrent().getPage().addJavaScript("http://192.168.88.29/web-apps/apps/api/documents/api.js");
//        UI.getCurrent().getPage().addJavaScript("http://172.16.2.88/web-apps/apps/api/documents/api.js");

        UI.getCurrent().getPage().addJavaScript(docEditorServer.toString());
        
        this.config = config.toString();
        getElement().callJsFunction("initComponent",this.config);
    }
    
    private void init() {
        LOGGER.log(Level.INFO, "Iniciando OOEditor...");
        LOGGER.log(Level.FINER, "Iniciando OOEditor...");
        LOGGER.log(Level.FINEST, "Iniciando OOEditor...");
        try {
            // obtener el CommandService directamente desde la URL
            this.commandService = new URL(this.editorServer.getProtocol()+"://"+
                                          this.editorServer.getHost()+
                                          (this.editorServer.getPort()>0?":"+this.editorServer.getPort():"")+
                                          "/coauthoring/CommandService.ashx");
            LOGGER.log(Level.FINEST, "commandService: "+this.commandService.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(OOEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public OOEditor withCommandServiceURL(URL cs) {
        this.commandService = cs;
        return this;
    }
    
    public String getDocumentKey() {
        JSONObject c = new JSONObject(this.config);
        String key = (String)c.query("/document/key");
        return key;
    }
    
    public int forceSave() {
        LOGGER.log(Level.FINEST, "ForceSave detectado");
        int errorCode = 0;
        try {
            JSONObject fs = new JSONObject();
            fs.put("c", "forcesave");
            fs.put("key", getDocumentKey());
                    
            LOGGER.log(Level.FINEST, "post command: "+fs.toString(4));
            
            HttpResponse response = Request.Post("http://172.16.2.88/coauthoring/CommandService.ashx")
                    .bodyString(fs.toString(), ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            
            LOGGER.log(Level.FINEST, "res: "+response.getStatusLine());
            LOGGER.log(Level.FINEST, "res: "+EntityUtils.toString(response.getEntity()));

            String res = EntityUtils.toString(response.getEntity());
            JSONObject ec = new JSONObject(res);
            
            errorCode = ec.getNumber("error").intValue();
            
            LOGGER.log(Level.FINEST, "res.body:" + res);
            LOGGER.log(Level.FINEST, "errorCode:" + errorCode);
        } catch (IOException ex) {
            Logger.getLogger(OOConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return errorCode;
    }
}

