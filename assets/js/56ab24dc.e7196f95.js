(window.webpackJsonp=window.webpackJsonp||[]).push([[17],{116:function(e,t,r){"use strict";r.d(t,"a",(function(){return m})),r.d(t,"b",(function(){return f}));var n=r(0),o=r.n(n);function a(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function c(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function u(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?c(Object(r),!0).forEach((function(t){a(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):c(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function i(e,t){if(null==e)return{};var r,n,o=function(e,t){if(null==e)return{};var r,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(o[r]=e[r]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(o[r]=e[r])}return o}var l=o.a.createContext({}),p=function(e){var t=o.a.useContext(l),r=t;return e&&(r="function"==typeof e?e(t):u(u({},t),e)),r},m=function(e){var t=p(e.components);return o.a.createElement(l.Provider,{value:t},e.children)},s={inlineCode:"code",wrapper:function(e){var t=e.children;return o.a.createElement(o.a.Fragment,{},t)}},d=o.a.forwardRef((function(e,t){var r=e.components,n=e.mdxType,a=e.originalType,c=e.parentName,l=i(e,["components","mdxType","originalType","parentName"]),m=p(r),d=n,f=m["".concat(c,".").concat(d)]||m[d]||s[d]||a;return r?o.a.createElement(f,u(u({ref:t},l),{},{components:r})):o.a.createElement(f,u({ref:t},l))}));function f(e,t){var r=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var a=r.length,c=new Array(a);c[0]=d;var u={};for(var i in t)hasOwnProperty.call(t,i)&&(u[i]=t[i]);u.originalType=e,u.mdxType="string"==typeof e?e:n,c[1]=u;for(var l=2;l<a;l++)c[l]=r[l];return o.a.createElement.apply(null,c)}return o.a.createElement.apply(null,r)}d.displayName="MDXCreateElement"},86:function(e,t,r){"use strict";r.r(t),r.d(t,"frontMatter",(function(){return u})),r.d(t,"metadata",(function(){return i})),r.d(t,"toc",(function(){return l})),r.d(t,"default",(function(){return m}));var n=r(3),o=r(7),a=(r(0),r(116)),c=["components"],u={id:"emulatorcheck",title:"Emulator check",slug:"/emulatorcheck"},i={unversionedId:"emulatorcheck",id:"emulatorcheck",isDocsHomePage:!1,title:"Emulator check",description:"The emulator checks are done using OS Configuration. For example:",source:"@site/docs/emulatorcheck.mdx",sourceDirName:".",slug:"/emulatorcheck",permalink:"/safe_to_run/docs/emulatorcheck",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/emulatorcheck.mdx",version:"current",frontMatter:{id:"emulatorcheck",title:"Emulator check",slug:"/emulatorcheck"},sidebar:"someSidebar",previous:{title:"OS Check",permalink:"/safe_to_run/docs/oscheck"},next:{title:"Debug check",permalink:"/safe_to_run/docs/debugcheck"}},l=[],p={toc:l};function m(e){var t=e.components,r=Object(o.a)(e,c);return Object(a.b)("wrapper",Object(n.a)({},p,r,{components:t,mdxType:"MDXLayout"}),Object(a.b)("p",null,"The emulator checks are done using OS Configuration. For example:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"osDetectionCheck(banAvdEmulator()).error()\n")),Object(a.b)("p",null,"Here are the checks supported so far:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"// The default android emulator\nbanAvdEmulator()\n\n// Ban genymotion emulators\nbanGenymotionEmulator()\n")),Object(a.b)("p",null,"More information, including the motivation for emulator detection can be found in this article:"),Object(a.b)("p",null,Object(a.b)("a",{parentName:"p",href:"https://danielllewellyn.medium.com/emulator-detection-in-android-350efba44048"},"Emulator detection on android")))}m.isMDXComponent=!0}}]);