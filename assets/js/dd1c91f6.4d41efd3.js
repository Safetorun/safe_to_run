(window.webpackJsonp=window.webpackJsonp||[]).push([[43],{113:function(e,n,t){"use strict";t.r(n),t.d(n,"frontMatter",(function(){return c})),t.d(n,"metadata",(function(){return l})),t.d(n,"toc",(function(){return u})),t.d(n,"default",(function(){return p}));var r=t(3),o=t(8),a=(t(0),t(123)),i=["components"],c={id:"reporting_or_default",title:"Reporting or default?",slug:"/reportingordefault"},l={unversionedId:"reporting_or_default",id:"reporting_or_default",isDocsHomePage:!1,title:"Reporting or default?",description:"Safe to run supports three modes of operation. Default, reporting or off device (off device is covered later)",source:"@site/docs/reporting_or_default.mdx",sourceDirName:".",slug:"/reportingordefault",permalink:"/safe_to_run/docs/reportingordefault",editUrl:"https://github.com/safetorun/safe_to_run/edit/master/docs/docs/reporting_or_default.mdx",version:"current",frontMatter:{id:"reporting_or_default",title:"Reporting or default?",slug:"/reportingordefault"},sidebar:"someSidebar",previous:{title:"Intent verification",permalink:"/safe_to_run/docs/intentverification"},next:{title:"Safe to run reporting",permalink:"/safe_to_run/docs/safetorunreporting"}},u=[{value:"Reporting",id:"reporting",children:[]}],s={toc:u};function p(e){var n=e.components,t=Object(o.a)(e,i);return Object(a.b)("wrapper",Object(r.a)({},s,t,{components:n,mdxType:"MDXLayout"}),Object(a.b)("p",null,"Safe to run supports three modes of operation. Default, reporting or off device (off device is covered later)"),Object(a.b)("p",null,"The recommended approach is to use the default option. I.e."),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"safeToRun(buildSafeToRunCheckList {\n     add {\n        banAvdEmulatorCheck()\n     }\n})\n")),Object(a.b)("p",null,"It also recommended that if you create a function for this check, that function should also be inlined. E.g."),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"private inline fun Context.canIRun(actionOnFailure: () -> Unit) =\n      if (safeToRun(buildSafeToRunCheckList {\n              add {\n                  banAvdEmulatorCheck()\n              }\n       }).invoke()) {\n          actionOnFailure()\n      }\n")),Object(a.b)("p",null,"And then call this function, e.g. from your fragments or activities:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"canIRun { throw RuntimeException() }\n")),Object(a.b)("p",null,"It is also recommended that you call this check more than once, in order to make it harder for attackers to reverse\nengineer the application and remove safe to run checks."),Object(a.b)("h3",{id:"reporting"},"Reporting"),Object(a.b)("p",null,"Another approach is to use the SafeToRun reporting API. This provides a 'Safe to run report' which contains extra\ninformation about any failures, and is particularly useful for providing error messages to developers about why\na check has failed. For example:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},'SafeToRun.init(\n    configure {\n\n        // Root beer (detect root)\n        rootDetection {\n            tolerateBusyBox = true\n        }.error()\n\n        // Black list certain apps\n        blacklistConfiguration {\n            +"com.abc.def"\n            +packageName\n            blacklistRootingApps()\n        }.error()\n\n        verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")\n            .error()\n\n        // OS Blacklist version\n        osDetectionCheck(\n            conditionalBuilder {\n                with(minOsVersion(MIN_OS_VERSION))\n                and(notManufacturer("Abc"))\n                and(bannedModel("bannedModel"))\n            }\n        ).warn()\n\n        osDetectionCheck(\n            conditionalBuilder {\n                with(bannedModel("Pixel 4a (5G)"))\n            }\n        ).warn()\n\n        osDetectionCheck(banBluestacksEmulator()).error()\n\n        installOriginCheckWithDefaults().warn()\n\n        osDetectionCheck(banAvdEmulator()).error()\n        debugCheck().warn()\n    }\n)\n')),Object(a.b)("p",null,"Then you can run a check from anywhere:"),Object(a.b)("pre",null,Object(a.b)("code",{parentName:"pre",className:"language-kotlin"},"SafeToRun.isSafeToRun()\n")),Object(a.b)("p",null,"Whilst this is easier to use and easier to configure, it is also easier for reverse engineers to\nbypass the checks no matter how many times you call ",Object(a.b)("inlineCode",{parentName:"p"},"isSafeToRun")," an attacker would only need to change\none bit of code to remove all checks"),Object(a.b)("p",null,"::: tip"),Object(a.b)("p",null,"Rule of thumb: Use the default check when using SafeToRun."),Object(a.b)("p",null,":::"))}p.isMDXComponent=!0},123:function(e,n,t){"use strict";t.d(n,"a",(function(){return p})),t.d(n,"b",(function(){return b}));var r=t(0),o=t.n(r);function a(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function i(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function c(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?i(Object(t),!0).forEach((function(n){a(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):i(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function l(e,n){if(null==e)return{};var t,r,o=function(e,n){if(null==e)return{};var t,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)t=a[r],n.indexOf(t)>=0||(o[t]=e[t]);return o}(e,n);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)t=a[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(o[t]=e[t])}return o}var u=o.a.createContext({}),s=function(e){var n=o.a.useContext(u),t=n;return e&&(t="function"==typeof e?e(n):c(c({},n),e)),t},p=function(e){var n=s(e.components);return o.a.createElement(u.Provider,{value:n},e.children)},f={inlineCode:"code",wrapper:function(e){var n=e.children;return o.a.createElement(o.a.Fragment,{},n)}},d=o.a.forwardRef((function(e,n){var t=e.components,r=e.mdxType,a=e.originalType,i=e.parentName,u=l(e,["components","mdxType","originalType","parentName"]),p=s(t),d=r,b=p["".concat(i,".").concat(d)]||p[d]||f[d]||a;return t?o.a.createElement(b,c(c({ref:n},u),{},{components:t})):o.a.createElement(b,c({ref:n},u))}));function b(e,n){var t=arguments,r=n&&n.mdxType;if("string"==typeof e||r){var a=t.length,i=new Array(a);i[0]=d;var c={};for(var l in n)hasOwnProperty.call(n,l)&&(c[l]=n[l]);c.originalType=e,c.mdxType="string"==typeof e?e:r,i[1]=c;for(var u=2;u<a;u++)i[u]=t[u];return o.a.createElement.apply(null,i)}return o.a.createElement.apply(null,t)}d.displayName="MDXCreateElement"}}]);