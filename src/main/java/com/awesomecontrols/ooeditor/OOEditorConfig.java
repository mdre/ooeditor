/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomecontrols.ooeditor;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Marcelo D. Ré {@literal <marcelo.re@gmail.com>}
 */
public class OOEditorConfig {

    private final static Logger LOGGER = Logger.getLogger(OOEditorConfig.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.FINEST);
        }
    }

    /*
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
            }
     */
    private OOConfig ooconfig;
    private JSONObject config;

    OOEditorConfig(OOConfig ooc) {
        this.ooconfig = ooc;
        this.config = this.ooconfig.getConfig();
    }

    /**
     * Return to the OOConfig level.
     *
     * @return
     */
    public OOConfig build() {
        return this.ooconfig;
    }

    /**
     * user: {
     *       id: 'user id',
     *       name: 'user name',
     *       group: 'group name' // for customization.reviewPermissions parameter
     * }
     * @param fileNameWithPath
     * @return 
     */
    public OOEditorConfig withUser(String userID, String userName, String groupName) {
        JSONObject ec = config.optJSONObject("editorConfig");
        if (ec == null) {
            ec = new JSONObject();
        }

        JSONObject user = new JSONObject();

        user.put("id",userID);
        user.put("name",userName);
        user.put("group", groupName);
        
        ec.putOpt("user", user);
        config.put("editorConfig", ec);
        return this;
    }

    
}
