import styles from '@vaadin/flow-frontend/ooeditor/ooeditor.css';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
  <dom-module id="oo-editor-css" theme-for="oo-editor">
    <template><style>${styles}</style></template>
  </dom-module>`;
document.head.appendChild($_documentContainer.content);