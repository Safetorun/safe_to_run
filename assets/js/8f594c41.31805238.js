"use strict";(self.webpackChunkdocs=self.webpackChunkdocs||[]).push([[9396],{3905:function(e,n,t){t.d(n,{Zo:function(){return c},kt:function(){return p}});var r=t(7294);function a(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function o(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function l(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?o(Object(t),!0).forEach((function(n){a(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):o(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function i(e,n){if(null==e)return{};var t,r,a=function(e,n){if(null==e)return{};var t,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||(a[t]=e[t]);return a}(e,n);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var u=r.createContext({}),s=function(e){var n=r.useContext(u),t=n;return e&&(t="function"==typeof e?e(n):l(l({},n),e)),t},c=function(e){var n=s(e.components);return r.createElement(u.Provider,{value:n},e.children)},d={inlineCode:"code",wrapper:function(e){var n=e.children;return r.createElement(r.Fragment,{},n)}},f=r.forwardRef((function(e,n){var t=e.components,a=e.mdxType,o=e.originalType,u=e.parentName,c=i(e,["components","mdxType","originalType","parentName"]),f=s(t),p=a,m=f["".concat(u,".").concat(p)]||f[p]||d[p]||o;return t?r.createElement(m,l(l({ref:n},c),{},{components:t})):r.createElement(m,l({ref:n},c))}));function p(e,n){var t=arguments,a=n&&n.mdxType;if("string"==typeof e||a){var o=t.length,l=new Array(o);l[0]=f;var i={};for(var u in n)hasOwnProperty.call(n,u)&&(i[u]=n[u]);i.originalType=e,i.mdxType="string"==typeof e?e:a,l[1]=i;for(var s=2;s<o;s++)l[s]=t[s];return r.createElement.apply(null,l)}return r.createElement.apply(null,t)}f.displayName="MDXCreateElement"},8215:function(e,n,t){var r=t(7294);n.Z=function(e){var n=e.children,t=e.hidden,a=e.className;return r.createElement("div",{role:"tabpanel",hidden:t,className:a},n)}},6396:function(e,n,t){t.d(n,{Z:function(){return f}});var r=t(7462),a=t(7294),o=t(2389),l=t(9443);var i=function(){var e=(0,a.useContext)(l.Z);if(null==e)throw new Error('"useUserPreferencesContext" is used outside of "Layout" component.');return e},u=t(1968),s=t(6010),c="tabItem_vU9c";function d(e){var n,t,o,l=e.lazy,d=e.block,f=e.defaultValue,p=e.values,m=e.groupId,g=e.className,v=a.Children.map(e.children,(function(e){if((0,a.isValidElement)(e)&&void 0!==e.props.value)return e;throw new Error("Docusaurus error: Bad <Tabs> child <"+("string"==typeof e.type?e.type:e.type.name)+'>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.')})),h=null!=p?p:v.map((function(e){var n=e.props;return{value:n.value,label:n.label,attributes:n.attributes}})),b=(0,u.lx)(h,(function(e,n){return e.value===n.value}));if(b.length>0)throw new Error('Docusaurus error: Duplicate values "'+b.map((function(e){return e.value})).join(", ")+'" found in <Tabs>. Every value needs to be unique.');var k=null===f?f:null!=(n=null!=f?f:null==(t=v.find((function(e){return e.props.default})))?void 0:t.props.value)?n:null==(o=v[0])?void 0:o.props.value;if(null!==k&&!h.some((function(e){return e.value===k})))throw new Error('Docusaurus error: The <Tabs> has a defaultValue "'+k+'" but none of its children has the corresponding value. Available values are: '+h.map((function(e){return e.value})).join(", ")+". If you intend to show no default tab, use defaultValue={null} instead.");var y=i(),w=y.tabGroupChoices,O=y.setTabGroupChoices,T=(0,a.useState)(k),C=T[0],E=T[1],N=[],x=(0,u.o5)().blockElementScrollPositionUntilNextRender;if(null!=m){var R=w[m];null!=R&&R!==C&&h.some((function(e){return e.value===R}))&&E(R)}var S=function(e){var n=e.currentTarget,t=N.indexOf(n),r=h[t].value;r!==C&&(x(n),E(r),null!=m&&O(m,r))},D=function(e){var n,t=null;switch(e.key){case"ArrowRight":var r=N.indexOf(e.currentTarget)+1;t=N[r]||N[0];break;case"ArrowLeft":var a=N.indexOf(e.currentTarget)-1;t=N[a]||N[N.length-1]}null==(n=t)||n.focus()};return a.createElement("div",{className:"tabs-container"},a.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.Z)("tabs",{"tabs--block":d},g)},h.map((function(e){var n=e.value,t=e.label,o=e.attributes;return a.createElement("li",(0,r.Z)({role:"tab",tabIndex:C===n?0:-1,"aria-selected":C===n,key:n,ref:function(e){return N.push(e)},onKeyDown:D,onFocus:S,onClick:S},o,{className:(0,s.Z)("tabs__item",c,null==o?void 0:o.className,{"tabs__item--active":C===n})}),null!=t?t:n)}))),l?(0,a.cloneElement)(v.filter((function(e){return e.props.value===C}))[0],{className:"margin-vert--md"}):a.createElement("div",{className:"margin-vert--md"},v.map((function(e,n){return(0,a.cloneElement)(e,{key:n,hidden:e.props.value!==C})}))))}function f(e){var n=(0,o.Z)();return a.createElement(d,(0,r.Z)({key:String(n)},e))}},9825:function(e,n,t){t.r(n),t.d(n,{frontMatter:function(){return s},contentTitle:function(){return c},metadata:function(){return d},toc:function(){return f},default:function(){return m}});var r=t(7462),a=t(3366),o=(t(7294),t(3905)),l=t(6396),i=t(8215),u=["components"],s={id:"gettingstarted",title:"Getting started",slug:"/gettingstarted"},c=void 0,d={unversionedId:"checks/gettingstarted",id:"checks/gettingstarted",title:"Getting started",description:"Gradle:",source:"@site/docs/checks/gettingstarted.mdx",sourceDirName:"checks",slug:"/gettingstarted",permalink:"/safe_to_run/docs/gettingstarted",editUrl:"https://github.com/safetorun/safe_to_run/edit/master/docs/docs/checks/gettingstarted.mdx",tags:[],version:"current",frontMatter:{id:"gettingstarted",title:"Getting started",slug:"/gettingstarted"},sidebar:"someSidebar",previous:{title:"Why Safe to Run?",permalink:"/safe_to_run/docs/"},next:{title:"Combine checks",permalink:"/safe_to_run/docs/combinechecks"}},f=[{value:"Errors or Warns",id:"errors-or-warns",children:[],level:2}],p={toc:f};function m(e){var n=e.components,t=(0,a.Z)(e,u);return(0,o.kt)("wrapper",(0,r.Z)({},p,t,{components:n,mdxType:"MDXLayout"}),(0,o.kt)("p",null,"Gradle:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-groovy"},'implementation "io.github.dllewellyn.safetorun:safetorun:$latest_version"\nimplementation "io.github.dllewellyn.safetorun:safeToRunCore:$latest_version"\n')),(0,o.kt)("p",null,"An example of a configuration, done in App class:"),(0,o.kt)(l.Z,{defaultValue:"default",values:[{label:"Safe to run (Default)",value:"default"},{label:"Safe to run reporting",value:"reporting"}],mdxType:"Tabs"},(0,o.kt)(i.Z,{value:"default",mdxType:"TabItem"},(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},'private inline fun canIRun(actionOnFailure: () -> Unit) {\n    if (safeToRun(buildSafeToRunCheckList {\n    add {\n        banAvdEmulatorCheck()\n    }\n\n    add {\n        blacklistedAppCheck()\n    }\n\n    add {\n        rootDetectionCheck()\n    }\n\n    add {\n        banGenymotionEmulatorCheck()\n    }\n\n    add {\n        banBluestacksEmulatorCheck()\n    }\n\n    add {\n        safeToRunCombinedCheck(\n            listOf(\n                { bannedHardwareCheck("hardware") },\n                { bannedBoardCheck("board") }\n            ) // Only one list to the combined check - fail if either condition is true\n        )\n    }\n\n    add {\n        safeToRunCombinedCheck(\n            listOf { installOriginCheckWithDefaultsCheck() }, // Fail if the install origin check fails\n            listOf { !BuildConfig.DEBUG } // Unless this is a debug application\n        )\n    }\n\n    add {\n        verifySignatureCheck("Abc")\n    }\n    }).invoke()) {\n        actionOnFailure()\n    }\n}\n')),(0,o.kt)("p",null,"Then use like this"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},'canIRun {\n    throw RuntimeException("Def")\n}\n'))),(0,o.kt)(i.Z,{value:"reporting",mdxType:"TabItem"},(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},'SafeToRun.init(\n    configure {\n\n        // Root beer (detect root)\n        rootDetection {\n            tolerateRoot = false\n            tolerateBusyBox = true\n        }.error()\n\n        // Black list certain apps\n        blacklistConfiguration {\n            +"com.abc.def"\n            +"com.google.earth"\n        }.error()\n\n        verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")\n            .error()\n\n        // OS Blacklist version\n        osDetectionCheck(\n            conditionalBuilder {\n                with(minOsVersion(22))\n                and(notManufacturer("Abc"))\n            }\n        ).warn()\n\n        debugCheck().warn()\n\n        installOriginCheckWithDefaults().error()\n    }\n)\n')),(0,o.kt)("p",null,"Then use like this"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},"SafeToRun.safeToRun()\n")),(0,o.kt)("h2",{id:"errors-or-warns"},"Errors or Warns"),(0,o.kt)("p",null,"The syntax above allows you to configure an 'error' or a 'warn' this simply impacts to type of response that is returned\nfrom safe to run. In practice, a response that would return an error message if configure to 'errorIf' will return"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},"data class SafeToRunReportFailure(val failureReason: String, val failureMessage: String) : SafeToRunReport()\n")),(0,o.kt)("p",null,"If set to warn it will return"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},"data class SafeToRunWarning(val warnReason: String, val warningMessage: String) : SafeToRunReport()\n")),(0,o.kt)("p",null,"The difference is simply to help your app to distinguish between the two"))))}m.isMDXComponent=!0}}]);