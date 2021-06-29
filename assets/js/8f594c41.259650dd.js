(window.webpackJsonp=window.webpackJsonp||[]).push([[30],{101:function(e,n,t){"use strict";t.r(n),t.d(n,"frontMatter",(function(){return s})),t.d(n,"metadata",(function(){return u})),t.d(n,"toc",(function(){return f})),t.d(n,"default",(function(){return b}));var r=t(3),a=t(8),o=(t(0),t(123)),i=t(130),c=t(131),l=["components"],s={id:"gettingstarted",title:"Getting started",slug:"/gettingstarted"},u={unversionedId:"checks/gettingstarted",id:"checks/gettingstarted",isDocsHomePage:!1,title:"Getting started",description:"Gradle:",source:"@site/docs/checks/gettingstarted.mdx",sourceDirName:"checks",slug:"/gettingstarted",permalink:"/safe_to_run/docs/gettingstarted",editUrl:"https://github.com/safetorun/safe_to_run/edit/master/docs/docs/checks/gettingstarted.mdx",version:"current",frontMatter:{id:"gettingstarted",title:"Getting started",slug:"/gettingstarted"},sidebar:"someSidebar",previous:{title:"Why Safe to Run?",permalink:"/safe_to_run/docs/"},next:{title:"Combine checks",permalink:"/safe_to_run/docs/combinechecks"}},f=[{value:"Errors or Warns",id:"errors-or-warns",children:[]}],d={toc:f};function b(e){var n=e.components,t=Object(a.a)(e,l);return Object(o.b)("wrapper",Object(r.a)({},d,t,{components:n,mdxType:"MDXLayout"}),Object(o.b)("p",null,"Gradle:"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-groovy"},'implementation "io.github.dllewellyn.safetorun:safetorun:$latest_version"\nimplementation "io.github.dllewellyn.safetorun:safeToRunCore:$latest_version"\n')),Object(o.b)("p",null,"An example of a configuration, done in App class:"),Object(o.b)(i.a,{defaultValue:"default",values:[{label:"Safe to run (Default)",value:"default"},{label:"Safe to run reporting",value:"reporting"}],mdxType:"Tabs"},Object(o.b)(c.a,{value:"default",mdxType:"TabItem"},Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'private inline fun canIRun(actionOnFailure: () -> Unit) {\n    if (safeToRun(buildSafeToRunCheckList {\n    add {\n        banAvdEmulatorCheck()\n    }\n\n    add {\n        blacklistedAppCheck()\n    }\n\n    add {\n        rootDetectionCheck()\n    }\n\n    add {\n        banGenymotionEmulatorCheck()\n    }\n\n    add {\n        banBluestacksEmulatorCheck()\n    }\n\n    add {\n        safeToRunCombinedCheck(\n            listOf(\n                { bannedHardwareCheck("hardware") },\n                { bannedBoardCheck("board") }\n            ) // Only one list to the combined check - fail if either condition is true\n        )\n    }\n\n    add {\n        safeToRunCombinedCheck(\n            listOf { installOriginCheckWithDefaultsCheck() }, // Fail if the install origin check fails\n            listOf { !BuildConfig.DEBUG } // Unless this is a debug application\n        )\n    }\n\n    add {\n        verifySignatureCheck("Abc")\n    }\n    }).invoke()) {\n        actionOnFailure()\n    }\n}\n')),Object(o.b)("p",null,"Then use like this"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'canIRun {\n    throw RuntimeException("Def")\n}\n'))),Object(o.b)(c.a,{value:"reporting",mdxType:"TabItem"},Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'SafeToRun.init(\n    configure {\n\n        // Root beer (detect root)\n        rootDetection {\n            tolerateRoot = false\n            tolerateBusyBox = true\n        }.error()\n\n        // Black list certain apps\n        blacklistConfiguration {\n            +"com.abc.def"\n            +"com.google.earth"\n        }.error()\n\n        verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")\n            .error()\n\n        // OS Blacklist version\n        osDetectionCheck(\n            conditionalBuilder {\n                with(minOsVersion(22))\n                and(notManufacturer("Abc"))\n            }\n        ).warn()\n\n        debugCheck().warn()\n\n        installOriginCheckWithDefaults().error()\n    }\n)\n')),Object(o.b)("p",null,"Then use like this"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},"SafeToRun.safeToRun()\n")),Object(o.b)("h2",{id:"errors-or-warns"},"Errors or Warns"),Object(o.b)("p",null,"The syntax above allows you to configure an 'error' or a 'warn' this simply impacts to type of response that is returned\nfrom safe to run. In practice, a response that would return an error message if configure to 'errorIf' will return"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},"data class SafeToRunReportFailure(val failureReason: String, val failureMessage: String) : SafeToRunReport()\n")),Object(o.b)("p",null,"If set to warn it will return"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},"data class SafeToRunWarning(val warnReason: String, val warningMessage: String) : SafeToRunReport()\n")),Object(o.b)("p",null,"The difference is simply to help your app to distinguish between the two"))))}b.isMDXComponent=!0},123:function(e,n,t){"use strict";t.d(n,"a",(function(){return f})),t.d(n,"b",(function(){return p}));var r=t(0),a=t.n(r);function o(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function i(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function c(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?i(Object(t),!0).forEach((function(n){o(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):i(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function l(e,n){if(null==e)return{};var t,r,a=function(e,n){if(null==e)return{};var t,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||(a[t]=e[t]);return a}(e,n);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var s=a.a.createContext({}),u=function(e){var n=a.a.useContext(s),t=n;return e&&(t="function"==typeof e?e(n):c(c({},n),e)),t},f=function(e){var n=u(e.components);return a.a.createElement(s.Provider,{value:n},e.children)},d={inlineCode:"code",wrapper:function(e){var n=e.children;return a.a.createElement(a.a.Fragment,{},n)}},b=a.a.forwardRef((function(e,n){var t=e.components,r=e.mdxType,o=e.originalType,i=e.parentName,s=l(e,["components","mdxType","originalType","parentName"]),f=u(t),b=r,p=f["".concat(i,".").concat(b)]||f[b]||d[b]||o;return t?a.a.createElement(p,c(c({ref:n},s),{},{components:t})):a.a.createElement(p,c({ref:n},s))}));function p(e,n){var t=arguments,r=n&&n.mdxType;if("string"==typeof e||r){var o=t.length,i=new Array(o);i[0]=b;var c={};for(var l in n)hasOwnProperty.call(n,l)&&(c[l]=n[l]);c.originalType=e,c.mdxType="string"==typeof e?e:r,i[1]=c;for(var s=2;s<o;s++)i[s]=t[s];return a.a.createElement.apply(null,i)}return a.a.createElement.apply(null,t)}b.displayName="MDXCreateElement"},124:function(e,n,t){"use strict";function r(e){var n,t,a="";if("string"==typeof e||"number"==typeof e)a+=e;else if("object"==typeof e)if(Array.isArray(e))for(n=0;n<e.length;n++)e[n]&&(t=r(e[n]))&&(a&&(a+=" "),a+=t);else for(n in e)e[n]&&(a&&(a+=" "),a+=n);return a}n.a=function(){for(var e,n,t=0,a="";t<arguments.length;)(e=arguments[t++])&&(n=r(e))&&(a&&(a+=" "),a+=n);return a}},128:function(e,n,t){"use strict";var r=t(0),a=t(129);n.a=function(){var e=Object(r.useContext)(a.a);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e}},129:function(e,n,t){"use strict";var r=t(0),a=Object(r.createContext)(void 0);n.a=a},130:function(e,n,t){"use strict";var r=t(0),a=t.n(r),o=t(128),i=t(124),c=t(56),l=t.n(c);var s=37,u=39;n.a=function(e){var n=e.lazy,t=e.block,c=e.defaultValue,f=e.values,d=e.groupId,b=e.className,p=Object(o.a)(),m=p.tabGroupChoices,g=p.setTabGroupChoices,h=Object(r.useState)(c),v=h[0],O=h[1],y=r.Children.toArray(e.children),k=[];if(null!=d){var j=m[d];null!=j&&j!==v&&f.some((function(e){return e.value===j}))&&O(j)}var w=function(e){var n=e.currentTarget,t=k.indexOf(n),r=f[t].value;O(r),null!=d&&(g(d,r),setTimeout((function(){var e,t,r,a,o,i,c,s;(e=n.getBoundingClientRect(),t=e.top,r=e.left,a=e.bottom,o=e.right,i=window,c=i.innerHeight,s=i.innerWidth,t>=0&&o<=s&&a<=c&&r>=0)||(n.scrollIntoView({block:"center",behavior:"smooth"}),n.classList.add(l.a.tabItemActive),setTimeout((function(){return n.classList.remove(l.a.tabItemActive)}),2e3))}),150))},C=function(e){var n,t;switch(e.keyCode){case u:var r=k.indexOf(e.target)+1;t=k[r]||k[0];break;case s:var a=k.indexOf(e.target)-1;t=k[a]||k[k.length-1]}null===(n=t)||void 0===n||n.focus()};return a.a.createElement("div",{className:"tabs-container"},a.a.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:Object(i.a)("tabs",{"tabs--block":t},b)},f.map((function(e){var n=e.value,t=e.label;return a.a.createElement("li",{role:"tab",tabIndex:v===n?0:-1,"aria-selected":v===n,className:Object(i.a)("tabs__item",l.a.tabItem,{"tabs__item--active":v===n}),key:n,ref:function(e){return k.push(e)},onKeyDown:C,onFocus:w,onClick:w},t)}))),n?Object(r.cloneElement)(y.filter((function(e){return e.props.value===v}))[0],{className:"margin-vert--md"}):a.a.createElement("div",{className:"margin-vert--md"},y.map((function(e,n){return Object(r.cloneElement)(e,{key:n,hidden:e.props.value!==v})}))))}},131:function(e,n,t){"use strict";var r=t(0),a=t.n(r);n.a=function(e){var n=e.children,t=e.hidden,r=e.className;return a.a.createElement("div",{role:"tabpanel",hidden:t,className:r},n)}}}]);