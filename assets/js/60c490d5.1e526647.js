(window.webpackJsonp=window.webpackJsonp||[]).push([[21],{119:function(e,t,n){"use strict";n.d(t,"a",(function(){return p})),n.d(t,"b",(function(){return f}));var a=n(0),r=n.n(a);function o(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){o(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function l(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},o=Object.keys(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(a=0;a<o.length;a++)n=o[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var s=r.a.createContext({}),u=function(e){var t=r.a.useContext(s),n=t;return e&&(n="function"==typeof e?e(t):c(c({},t),e)),n},p=function(e){var t=u(e.components);return r.a.createElement(s.Provider,{value:t},e.children)},b={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},d=r.a.forwardRef((function(e,t){var n=e.components,a=e.mdxType,o=e.originalType,i=e.parentName,s=l(e,["components","mdxType","originalType","parentName"]),p=u(n),d=a,f=p["".concat(i,".").concat(d)]||p[d]||b[d]||o;return n?r.a.createElement(f,c(c({ref:t},s),{},{components:n})):r.a.createElement(f,c({ref:t},s))}));function f(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var o=n.length,i=new Array(o);i[0]=d;var c={};for(var l in t)hasOwnProperty.call(t,l)&&(c[l]=t[l]);c.originalType=e,c.mdxType="string"==typeof e?e:a,i[1]=c;for(var s=2;s<o;s++)i[s]=n[s];return r.a.createElement.apply(null,i)}return r.a.createElement.apply(null,n)}d.displayName="MDXCreateElement"},120:function(e,t,n){"use strict";function a(e){var t,n,r="";if("string"==typeof e||"number"==typeof e)r+=e;else if("object"==typeof e)if(Array.isArray(e))for(t=0;t<e.length;t++)e[t]&&(n=a(e[t]))&&(r&&(r+=" "),r+=n);else for(t in e)e[t]&&(r&&(r+=" "),r+=t);return r}t.a=function(){for(var e,t,n=0,r="";n<arguments.length;)(e=arguments[n++])&&(t=a(e))&&(r&&(r+=" "),r+=t);return r}},124:function(e,t,n){"use strict";var a=n(0),r=n(125);t.a=function(){var e=Object(a.useContext)(r.a);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e}},125:function(e,t,n){"use strict";var a=n(0),r=Object(a.createContext)(void 0);t.a=r},126:function(e,t,n){"use strict";var a=n(0),r=n.n(a),o=n(124),i=n(120),c=n(56),l=n.n(c);var s=37,u=39;t.a=function(e){var t=e.lazy,n=e.block,c=e.defaultValue,p=e.values,b=e.groupId,d=e.className,f=Object(o.a)(),m=f.tabGroupChoices,g=f.setTabGroupChoices,v=Object(a.useState)(c),O=v[0],y=v[1],h=a.Children.toArray(e.children),j=[];if(null!=b){var k=m[b];null!=k&&k!==O&&p.some((function(e){return e.value===k}))&&y(k)}var w=function(e){var t=e.currentTarget,n=j.indexOf(t),a=p[n].value;y(a),null!=b&&(g(b,a),setTimeout((function(){var e,n,a,r,o,i,c,s;(e=t.getBoundingClientRect(),n=e.top,a=e.left,r=e.bottom,o=e.right,i=window,c=i.innerHeight,s=i.innerWidth,n>=0&&o<=s&&r<=c&&a>=0)||(t.scrollIntoView({block:"center",behavior:"smooth"}),t.classList.add(l.a.tabItemActive),setTimeout((function(){return t.classList.remove(l.a.tabItemActive)}),2e3))}),150))},x=function(e){var t,n;switch(e.keyCode){case u:var a=j.indexOf(e.target)+1;n=j[a]||j[0];break;case s:var r=j.indexOf(e.target)-1;n=j[r]||j[j.length-1]}null===(t=n)||void 0===t||t.focus()};return r.a.createElement("div",{className:"tabs-container"},r.a.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:Object(i.a)("tabs",{"tabs--block":n},d)},p.map((function(e){var t=e.value,n=e.label;return r.a.createElement("li",{role:"tab",tabIndex:O===t?0:-1,"aria-selected":O===t,className:Object(i.a)("tabs__item",l.a.tabItem,{"tabs__item--active":O===t}),key:t,ref:function(e){return j.push(e)},onKeyDown:x,onFocus:w,onClick:w},n)}))),t?Object(a.cloneElement)(h.filter((function(e){return e.props.value===O}))[0],{className:"margin-vert--md"}):r.a.createElement("div",{className:"margin-vert--md"},h.map((function(e,t){return Object(a.cloneElement)(e,{key:t,hidden:e.props.value!==O})}))))}},127:function(e,t,n){"use strict";var a=n(0),r=n.n(a);t.a=function(e){var t=e.children,n=e.hidden,a=e.className;return r.a.createElement("div",{role:"tabpanel",hidden:n,className:a},t)}},92:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return s})),n.d(t,"metadata",(function(){return u})),n.d(t,"toc",(function(){return p})),n.d(t,"default",(function(){return d}));var a=n(3),r=n(8),o=(n(0),n(119)),i=n(126),c=n(127),l=["components"],s={id:"blacklisting",title:"Blacklisting apps",slug:"/blacklist"},u={unversionedId:"blacklisting",id:"blacklisting",isDocsHomePage:!1,title:"Blacklisting apps",description:"For full information see here:",source:"@site/docs/blacklisting.mdx",sourceDirName:".",slug:"/blacklist",permalink:"/safe_to_run/docs/blacklist",editUrl:"https://github.com/safetorun/safe_to_run/edit/master/docs/docs/blacklisting.mdx",version:"current",frontMatter:{id:"blacklisting",title:"Blacklisting apps",slug:"/blacklist"},sidebar:"someSidebar",previous:{title:"Root detection",permalink:"/safe_to_run/docs/rootdetection"},next:{title:"OS Check",permalink:"/safe_to_run/docs/oscheck"}},p=[{value:"Configuration",id:"configuration",children:[]}],b={toc:p};function d(e){var t=e.components,n=Object(r.a)(e,l);return Object(o.b)("wrapper",Object(a.a)({},b,n,{components:t,mdxType:"MDXLayout"}),Object(o.b)("p",null,"For full information see here:"),Object(o.b)("p",null,Object(o.b)("a",{parentName:"p",href:"https://developer.android.com/training/package-visibility/declaring"},"https://developer.android.com/training/package-visibility/declaring")),Object(o.b)("p",null,"You'll need to declare android permissions, for example, the suggested way of doing this is by specifying your packages,\ne.g."),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-xml"},'\n<queries>\n    <package android:name="com.abc.def"/>\n</queries>\n')),Object(o.b)("p",null,"Or, declare all. That's not suggested"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-xml"},'<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"/>\n')),Object(o.b)("p",null,"We also have a utility function ",Object(o.b)("inlineCode",{parentName:"p"},"blacklistRootingApps()")," in order to detect apps that are used\nfor rooting an application"),Object(o.b)("h2",{id:"configuration"},"Configuration"),Object(o.b)(i.a,{defaultValue:"default",values:[{label:"Default",value:"default"},{label:"Reporting",value:"reporting"},{label:"Off device",value:"offdevice"}],mdxType:"Tabs"},Object(o.b)(c.a,{value:"default",mdxType:"TabItem"},Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'safeToRun(buildSafeToRunCheckList {\n    add { blacklistedAppCheck("com.blacklisted.package1", "com.blacklisted.package2") }\n})\n'))),Object(o.b)(c.a,{value:"reporting",mdxType:"TabItem"},Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'blacklistConfiguration {\n    +"com.abc.def"\n    +packageName\n    blacklistRootingApps()\n}.error()\n'))),Object(o.b)(c.a,{value:"offdevice",mdxType:"TabItem"},Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'blacklistedAppCheck(context) {\n    +"com.example.abc"\n}.error()\n')))))}d.isMDXComponent=!0}}]);