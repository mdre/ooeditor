/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomecontrols.ooeditor;

public enum OODocumentPermission {
    enableEdit("edit", true), //: <can edit>, // default = true
    enableDownload("download", true), //: <can download>, // default = true
    enableReader("reader", true), //: <can view in readable mode>,
    enableReview("review", true), //: <can review>, // default = edit
    enablePrint("print", true), //: <can print>, // default = true
    enableComment("comment", true), //: <can comment in view mode> // default = edit,
    enableModifyFilter("modifyFilter", true), //: <can add, remove and save filter in the spreadsheet> // default = true
    enableModifyContentControl("modifyContentControl", true), //: <can modify content controls in documenteditor> // default = true
    enableFillForms("fillForms", true), //:  <can edit forms in view mode> // default = edit || review,
    enableCopy("copy", true), //: <can copy data> // default = true,
    enableEditCommentAuthorOnly("editCommentAuthorOnly", true), //: <can edit your own comments only> // default = false
    enableDeleteCommentAuthorOnly("deleteCommentAuthorOnly", true), //: <can delete your own comments only> // default = false

    disableEdit("edit", false), //: <can edit>, // default = true
    disableDownload("download", false), //: <can download>, // default = true
    disableReader("reader", false), //: <can view in readable mode>,
    disableReview("review", false), //: <can review>, // default = edit
    disablePrint("print", false), //: <can print>, // default = true
    disableComment("comment", false), //: <can comment in view mode> // default = edit,
    disableModifyFilter("modifyFilter", false), //: <can add, remove and save filter in the spreadsheet> // default = true
    disableModifyContentControl("modifyContentControl", false), //: <can modify content controls in documenteditor> // default = true
    disableFillForms("fillForms", false), //:  <can edit forms in view mode> // default = edit || review,
    disableCopy("copy", false), //: <can copy data> // default = true,
    disableEditCommentAuthorOnly("editCommentAuthorOnly", false), //: <can edit your own comments only> // default = false
    disableDeleteCommentAuthorOnly("deleteCommentAuthorOnly", false); //: <can delete your own comments only> // default = false

    private final String label;
    private final boolean state;

    private OODocumentPermission(String label, boolean state) {
        this.label = label;
        this.state = state;
    }

    public String gePermision() {
        return this.label;
    }

    public boolean getState() {
        return this.state;
    }
}
