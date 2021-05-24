(window.webpackJsonp=window.webpackJsonp||[]).push([[37],{105:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return u})),n.d(t,"metadata",(function(){return l})),n.d(t,"toc",(function(){return s})),n.d(t,"default",(function(){return p}));var r=n(3),a=n(7),i=(n(0),n(115)),o=n(126),c=n(127),u={id:"signatureverification",title:"Signature verification",slug:"/signature"},l={unversionedId:"signatureverification",id:"signatureverification",isDocsHomePage:!1,title:"Signature verification",description:"Configuration",source:"@site/docs/signatureverification.mdx",sourceDirName:".",slug:"/signature",permalink:"/safe_to_run/docs/signature",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/signatureverification.mdx",version:"current",frontMatter:{id:"signatureverification",title:"Signature verification",slug:"/signature"},sidebar:"someSidebar",previous:{title:"Debug check",permalink:"/safe_to_run/docs/debugcheck"},next:{title:"Install origin",permalink:"/safe_to_run/docs/installorigin"}},s=[{value:"Configuration",id:"configuration",children:[]}],f={toc:s};function p(e){var t=e.components,n=Object(a.a)(e,["components"]);return Object(i.b)("wrapper",Object(r.a)({},f,n,{components:t,mdxType:"MDXLayout"}),Object(i.b)("h2",{id:"configuration"},"Configuration"),Object(i.b)(o.a,{defaultValue:"ondevice",values:[{label:"On device",value:"ondevice"},{label:"Off device",value:"offdevice"}],mdxType:"Tabs"},Object(i.b)(c.a,{value:"ondevice",mdxType:"TabItem"},Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-kotlin"},'verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=").error()\n'))),Object(i.b)(c.a,{value:"offdevice",mdxType:"TabItem"},Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-kotlin"},'verifySignatureCheck(context, "cSP1O3JN/8+Ag14WAOeOEnwAnpY=").error()\n')))),Object(i.b)("p",null,"To generate your signature, the simplest way is to simply run the report and read\nthe result, for example"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-kotlin"},'Log.v("Signature", verifySignatureConfig("").canRun())\n')),Object(i.b)("p",null,"Argument can take multiple strings so you can provide a signature for\ndebug, release (etc) builds"))}p.isMDXComponent=!0},115:function(e,t,n){"use strict";n.d(t,"a",(function(){return f})),n.d(t,"b",(function(){return d}));var r=n(0),a=n.n(r);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function u(e,t){if(null==e)return{};var n,r,a=function(e,t){if(null==e)return{};var n,r,a={},i=Object.keys(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||(a[n]=e[n]);return a}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(r=0;r<i.length;r++)n=i[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(a[n]=e[n])}return a}var l=a.a.createContext({}),s=function(e){var t=a.a.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):c(c({},t),e)),n},f=function(e){var t=s(e.components);return a.a.createElement(l.Provider,{value:t},e.children)},p={inlineCode:"code",wrapper:function(e){var t=e.children;return a.a.createElement(a.a.Fragment,{},t)}},b=a.a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,i=e.originalType,o=e.parentName,l=u(e,["components","mdxType","originalType","parentName"]),f=s(n),b=r,d=f["".concat(o,".").concat(b)]||f[b]||p[b]||i;return n?a.a.createElement(d,c(c({ref:t},l),{},{components:n})):a.a.createElement(d,c({ref:t},l))}));function d(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=n.length,o=new Array(i);o[0]=b;var c={};for(var u in t)hasOwnProperty.call(t,u)&&(c[u]=t[u]);c.originalType=e,c.mdxType="string"==typeof e?e:r,o[1]=c;for(var l=2;l<i;l++)o[l]=n[l];return a.a.createElement.apply(null,o)}return a.a.createElement.apply(null,n)}b.displayName="MDXCreateElement"},116:function(e,t,n){"use strict";function r(e){var t,n,a="";if("string"==typeof e||"number"==typeof e)a+=e;else if("object"==typeof e)if(Array.isArray(e))for(t=0;t<e.length;t++)e[t]&&(n=r(e[t]))&&(a&&(a+=" "),a+=n);else for(t in e)e[t]&&(a&&(a+=" "),a+=t);return a}t.a=function(){for(var e,t,n=0,a="";n<arguments.length;)(e=arguments[n++])&&(t=r(e))&&(a&&(a+=" "),a+=t);return a}},120:function(e,t,n){"use strict";var r=n(0),a=n(121);t.a=function(){var e=Object(r.useContext)(a.a);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e}},121:function(e,t,n){"use strict";var r=n(0),a=Object(r.createContext)(void 0);t.a=a},126:function(e,t,n){"use strict";var r=n(0),a=n.n(r),i=n(120),o=n(116),c=n(55),u=n.n(c);var l=37,s=39;t.a=function(e){var t=e.lazy,n=e.block,c=e.defaultValue,f=e.values,p=e.groupId,b=e.className,d=Object(i.a)(),m=d.tabGroupChoices,v=d.setTabGroupChoices,g=Object(r.useState)(c),O=g[0],y=g[1],h=r.Children.toArray(e.children),j=[];if(null!=p){var w=m[p];null!=w&&w!==O&&f.some((function(e){return e.value===w}))&&y(w)}var x=function(e){var t=e.currentTarget,n=j.indexOf(t),r=f[n].value;y(r),null!=p&&(v(p,r),setTimeout((function(){var e,n,r,a,i,o,c,l;(e=t.getBoundingClientRect(),n=e.top,r=e.left,a=e.bottom,i=e.right,o=window,c=o.innerHeight,l=o.innerWidth,n>=0&&i<=l&&a<=c&&r>=0)||(t.scrollIntoView({block:"center",behavior:"smooth"}),t.classList.add(u.a.tabItemActive),setTimeout((function(){return t.classList.remove(u.a.tabItemActive)}),2e3))}),150))},k=function(e){var t,n;switch(e.keyCode){case s:var r=j.indexOf(e.target)+1;n=j[r]||j[0];break;case l:var a=j.indexOf(e.target)-1;n=j[a]||j[j.length-1]}null===(t=n)||void 0===t||t.focus()};return a.a.createElement("div",{className:"tabs-container"},a.a.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:Object(o.a)("tabs",{"tabs--block":n},b)},f.map((function(e){var t=e.value,n=e.label;return a.a.createElement("li",{role:"tab",tabIndex:O===t?0:-1,"aria-selected":O===t,className:Object(o.a)("tabs__item",u.a.tabItem,{"tabs__item--active":O===t}),key:t,ref:function(e){return j.push(e)},onKeyDown:k,onFocus:x,onClick:x},n)}))),t?Object(r.cloneElement)(h.filter((function(e){return e.props.value===O}))[0],{className:"margin-vert--md"}):a.a.createElement("div",{className:"margin-vert--md"},h.map((function(e,t){return Object(r.cloneElement)(e,{key:t,hidden:e.props.value!==O})}))))}},127:function(e,t,n){"use strict";var r=n(0),a=n.n(r);t.a=function(e){var t=e.children,n=e.hidden,r=e.className;return a.a.createElement("div",{role:"tabpanel",hidden:n,className:r},t)}}}]);