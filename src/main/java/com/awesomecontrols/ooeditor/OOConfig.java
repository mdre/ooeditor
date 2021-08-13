/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomecontrols.ooeditor;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class OOConfig {

    private final static Logger LOGGER = Logger.getLogger(OOConfig.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.FINEST);
        }
    }

    private final HttpClient client = HttpClient.newHttpClient();
    JSONObject config;
    URL docStorageServer;
    String fileNameWithPath;


    /*

        # Full #

        config = {
            type: 'desktop or mobile or embedded',
            width: '100% by default',
            height: '100% by default',
            documentType: 'word' | 'cell' | 'slide',// deprecate 'text' | 'spreadsheet' | 'presentation',
            token: <string> encrypted signature
            document: {
                title: 'document title',
                url: 'document url'
                fileType: 'document file type',
                options: <advanced options>,
                key: 'key',
                vkey: 'vkey',
                info: {
                    owner: 'owner name',
                    folder: 'path to document',
                    uploaded: '<uploaded date>',
                    sharingSettings: [
                        {
                            user: 'user name',
                            permissions: '<permissions>',
                            isLink: false
                        },
                        ...
                    ],
                    favorite: '<file is favorite>' // true/false/undefined (undefined - don't show fav. button)
                },
                permissions: {
                    edit: <can edit>, // default = true
                    download: <can download>, // default = true
                    reader: <can view in readable mode>,
                    review: <can review>, // default = edit
                    print: <can print>, // default = true
                    comment: <can comment in view mode> // default = edit,
                    modifyFilter: <can add, remove and save filter in the spreadsheet> // default = true
                    modifyContentControl: <can modify content controls in documenteditor> // default = true
                    fillForms:  <can edit forms in view mode> // default = edit || review,
                    copy: <can copy data> // default = true,
                    editCommentAuthorOnly: <can edit your own comments only> // default = false
                    deleteCommentAuthorOnly: <can delete your own comments only> // default = false,
                    reviewGroups: ["Group1", ""] // current user can accept/reject review changes made by users from Group1 and users without a group. [] - use groups, but can't change any group's changes
                }
            },
            editorConfig: {
                actionLink: { // open file and scroll to data, used with onMakeActionLink or the onRequestSendNotify event
                    action: {
                        type: "bookmark", // or type="comment"
                        data: <bookmark name> // or comment id
                    }
                },
                mode: 'view or edit',
                lang: <language code>,
                location: <location>,
                canCoAuthoring: <can coauthoring documents>,
                canBackToFolder: <can return to folder> - deprecated. use "customization.goback" parameter,
                createUrl: 'create document url', 
                sharingSettingsUrl: 'document sharing settings url',
                fileChoiceUrl: 'source url', // for mail merge or image from storage
                callbackUrl: <url for connection between sdk and portal>,
                mergeFolderUrl: 'folder for saving merged file', // must be deprecated, use saveAsUrl instead
                saveAsUrl: 'folder for saving files'
                licenseUrl: <url for license>,
                customerId: <customer id>,
                region: <regional settings> // can be 'en-us' or lang code

                user: {
                    id: 'user id',
                    name: 'user name',
                    group: 'group name' // for customization.reviewPermissions parameter
                },
                recent: [
                    {
                        title: 'document title',
                        url: 'document url',
                        folder: 'path to document',
                    },
                    ...
                ],
                templates: [
                    {
                        title: 'template name', // name - is deprecated
                        image: 'template icon url',
                        url: 'http://...'
                    },
                    ...
                ],
                customization: {
                    logo: {
                        image: url,
                        imageEmbedded: url,
                        url: http://...
                    },
                    customer: {
                        name: 'SuperPuper',
                        address: 'New-York, 125f-25',
                        mail: 'support@gmail.com',
                        www: 'www.superpuper.com',
                        info: 'Some info',
                        logo: ''
                    },
                    about: true,
                    feedback: {
                        visible: false,
                        url: http://...
                    },
                    goback: {
                        url: 'http://...',
                        text: 'Go to London',
                        blank: true,
                        requestClose: false // if true - goback send onRequestClose event instead opening url
                    },
                    reviewPermissions: {
                        "Group1": ["Group2"], // users from Group1 can accept/reject review changes made by users from Group2
                        "Group2": ["Group1", "Group2"] // users from Group2 can accept/reject review changes made by users from Group1 and Group2
                        "Group3": [""] // users from Group3 can accept/reject review changes made by users without a group
                    },
                    anonymous: { // set name for anonymous user
                        request: bool (default: true), // enable set name
                        label: string (default: "Guest") // postfix for user name
                    }
                    chat: true,
                    comments: true,
                    zoom: 100,
                    compactToolbar: false,
                    leftMenu: true,
                    rightMenu: true,
                    hideRightMenu: false, // hide or show right panel on first loading
                    toolbar: true,
                    statusBar: true,
                    autosave: true,
                    forcesave: false,
                    commentAuthorOnly: false, // must be deprecated. use permissions.editCommentAuthorOnly and permissions.deleteCommentAuthorOnly instead
                    showReviewChanges: false,
                    help: true,
                    compactHeader: false,
                    toolbarNoTabs: false,
                    toolbarHideFileName: false,
                    reviewDisplay: 'original',
                    spellcheck: true,
                    compatibleFeatures: false,
                    unit: 'cm' // cm, pt, inch,
                    mentionShare : true // customize tooltip for mention,
                    macros: true // can run macros in document
                    plugins: true // can run plugins in document
                    macrosMode: 'warn' // warn about automatic macros, 'enable', 'disable', 'warn',
                    trackChanges: undefined // true/false - open editor with track changes mode on/off,
                    hideRulers: false, // hide or show rulers on first loading (presentation or document editor)
                },
                plugins: {
                    autostart: ['asc.{FFE1F462-1EA2-4391-990D-4CC84940B754}'],
                    pluginsData: [
                        "helloworld/config.json",
                        "chess/config.json",
                        "speech/config.json",
                        "clipart/config.json",
                    ]
                }
            },
            events: {
                'onAppReady': <application ready callback>,
                'onDocumentStateChange': <document state changed callback>
                'onDocumentReady': <document ready callback>
                'onRequestEditRights': <request rights for switching from view to edit>,
                'onRequestHistory': <request version history>,// must call refreshHistory method
                'onRequestHistoryData': <request version data>,// must call setHistoryData method
                'onRequestRestore': <try to restore selected version>,
                'onRequestHistoryClose': <request closing history>,
                'onError': <error callback>,
                'onWarning': <warning callback>,
                'onInfo': <document open callback>,// send view or edit mode
                'onOutdatedVersion': <outdated version callback>,// send when  previous version is opened
                'onDownloadAs': <download as callback>,// send url of downloaded file as a response for downloadAs method
                'onRequestSaveAs': <try to save copy of the document>,
                'onCollaborativeChanges': <co-editing changes callback>,// send when other user co-edit document
                'onRequestRename': <try to rename document>,
                'onMetaChange': // send when meta information changed
                'onRequestClose': <request close editor>,
                'onMakeActionLink': <request link to document with bookmark, comment...>,// must call setActionLink method
                'onRequestUsers': <request users list for mentions>,// must call setUsers method
                'onRequestSendNotify': //send when user is mentioned in a comment,
                'onRequestInsertImage': <try to insert image>,// must call insertImage method
                'onRequestCompareFile': <request file to compare>,// must call setRevisedFile method
                'onRequestSharingSettings': <request sharing settings>,// must call setSharingSettings method
                'onRequestCreateNew': <try to create document>,
            }
        }
     */
    
    
    private OODocumentConfig ooDocConfig;
    private OOEditorConfig ooEditorConfig;
    
    public OOConfig(URL docStorageServer) {
        this.docStorageServer = docStorageServer;
        config = new JSONObject();

        config.put("width", "100%");
        config.put("height", "100%");

        withCallbackURL();
        
        this.ooDocConfig = new OODocumentConfig(this);
        this.ooEditorConfig = new OOEditorConfig(this);
    }
    
    
    
    
    //=====================================================
    private void withCallbackURL() {
        JSONObject editorConfig = config.optJSONObject("editorConfig");
        if (editorConfig == null) {
            editorConfig = new JSONObject();
        }
        editorConfig.putOpt("callbackUrl", this.docStorageServer + "/api/callback");
        this.config.put("editorConfig", editorConfig);
    }

    /**
     * Path to the filename in the filesystem where de DocumentStorageServer
     * will find the file.
     *
     * @param fileNameWithPath
     *
     * @return
     */
    String getAutoKey(String fileNameWithPath) {
        LOGGER.log(Level.FINEST, "obtener una key");
        LOGGER.log(Level.FINEST, "fileName:" + fileNameWithPath);
        String key = "";
        try {

            var builder = HttpRequest.newBuilder().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
                    .POST(HttpRequest.BodyPublishers.ofString(fileNameWithPath));

            // request = builder.uri(URI.create("http://firma-web1-test:8080/signer-dev/fdrcallback/pop")).build();
            LOGGER.log(Level.FINEST, "uri: " + docStorageServer.toString() + "/api/getfilekey");
            var request = builder.uri(URI.create(
                    docStorageServer.toString() + "/api/getfilekey"
            )).build();
            // extraer el resultado de una firma
            LOGGER.log(Level.FINEST, "request: " + request);
            var res = client.send(request, HttpResponse.BodyHandlers.ofString());
            key = res.body();
            LOGGER.log(Level.FINEST, "res.body:" + res);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(OOConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.config.toString();
    }

    /**
     * Return the DocumentConfig reference to set parameters
     * @return 
     */
    public OODocumentConfig setDocumentConfig() {
        return this.ooDocConfig;
    }
    
    /**
     * Return the EditorConfig reference to set parameters
     * @return 
     */
    public OOEditorConfig setEditorConfig() {
        return this.ooEditorConfig;
    }
    
    public JSONObject getConfig() {
        return this.config;
    }
 
    URL getStorageServer() {
        return this.docStorageServer;
    }
}
