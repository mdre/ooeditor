import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import { ThemableMixin } from '@vaadin/vaadin-themable-mixin/vaadin-themable-mixin.js';
import "@vaadin/flow-frontend/ooeditor/ooeditor-css-loader.js";
//import /* webpackIgnore: true */ 'https://172.16.2.101/web-apps/apps/api/documents/api.js';
//import /* webpackIgnore: true */ 'http://192.168.88.29/web-apps/apps/api/documents/api.js';
/**
 * `PDF-Viewer`
 * A PDF Viewer component
 *
 * @customElement
 * @polymer
 */
class OOEditor extends ThemableMixin(PolymerElement) {
    static get template() {
        return html `
        <style>
        </style> 
        
        <div id="editor" class="editor"></div>
        `;
    }

    static get is() {
        return 'oo-editor';
    }

    static get properties() {
        return {
            targetid: {
                type: String,
                value: ''
            }
        };
    }


    log(...logVal) {
        if (this.logEnabled) {
            console.log(...logVal);
        }
    }

    constructor() {
        super();

        this.logEnabled = true;
    }

    initComponent(config) {
        this.log(config);
        var docConfig = JSON.parse(config);
        this.log(docConfig);
        this.log(this.$.editor);
        this.log(DocsAPI);
        this.log("Iniciando......");
        this.docEditor = new DocsAPI.DocEditor(this.$.editor, docConfig);
        this.log(this.docEditor);
        this.log("-------------------------");
    }

};

customElements.define(OOEditor.is, OOEditor);