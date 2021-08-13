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
public class OODocumentConfig {

    private final static Logger LOGGER = Logger.getLogger(OODocumentConfig.class.getName());

    static {
        if (LOGGER.getLevel() == null) {
            LOGGER.setLevel(Level.INFO);
        }
    }

    /*
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
            }
     */
    private OOConfig ooconfig;
    private JSONObject config;

    OODocumentConfig(OOConfig ooc) {
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

    public OODocumentConfig withFilename(String fileNameWithPath) {
        JSONObject d = config.optJSONObject("document");
        if (d == null) {
            d = new JSONObject();
        }

        //obtener la key y la referencia al archivo encriptada por el DSS.
        String key = this.ooconfig.getAutoKey(fileNameWithPath);

        System.out.println("key" + key);
        LOGGER.log(Level.FINEST, key);
        d.putOpt("key", key);
        d.putOpt("url", this.ooconfig.getStorageServer().toString() + "/api/download/" + key);

        System.out.println(this.ooconfig.getStorageServer().toString() + "/api/download/" + key);
        LOGGER.log(Level.FINEST, this.ooconfig.getStorageServer().toString() + "/api/download/" + key);
        config.put("document", d);
        return this;
    }

    public OODocumentConfig withType(String type) {
        JSONObject d = config.optJSONObject("document");
        if (d == null) {
            d = new JSONObject();
        }
        d.putOpt("fileType", type);

        config.put("document", d);
        return this;
    }

    public OODocumentConfig withTitle(String title) {
        JSONObject d = config.optJSONObject("document");
        if (d == null) {
            d = new JSONObject();
        }
        d.putOpt("title", title);

        config.put("document", d);
        return this;
    }

    public OODocumentConfig withPermissions(OODocumentPermission... permissions) {
        JSONObject d = config.optJSONObject("document");
        if (d == null) {
            d = new JSONObject();
        }

        JSONObject ps = new JSONObject();

        for (OODocumentPermission p : permissions) {
            ps.put(p.gePermision(), p.getState());
        }
        d.putOpt("permissions", ps);
        config.put("document", d);
        return this;
    }

}
