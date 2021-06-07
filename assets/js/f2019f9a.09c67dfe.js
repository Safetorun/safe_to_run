(window.webpackJsonp=window.webpackJsonp||[]).push([[40],{108:function(e,n,t){"use strict";t.r(n),t.d(n,"frontMatter",(function(){return u})),t.d(n,"metadata",(function(){return s})),t.d(n,"toc",(function(){return b})),t.d(n,"default",(function(){return f}));var r=t(3),a=t(7),o=(t(0),t(116)),i=t(127),c=t(128),l=["components"],u={id:"oscheck",title:"OS Check",slug:"/oscheck"},s={unversionedId:"oscheck",id:"oscheck",isDocsHomePage:!1,title:"OS Check",description:"There are the following options for configuration:",source:"@site/docs/oscheck.mdx",sourceDirName:".",slug:"/oscheck",permalink:"/safe_to_run/docs/oscheck",editUrl:"https://github.com/facebook/docusaurus/edit/master/website/docs/oscheck.mdx",version:"current",frontMatter:{id:"oscheck",title:"OS Check",slug:"/oscheck"},sidebar:"someSidebar",previous:{title:"Blacklisting apps",permalink:"/safe_to_run/docs/blacklist"},next:{title:"Emulator check",permalink:"/safe_to_run/docs/emulatorcheck"}},b=[{value:"Configuration",id:"configuration",children:[]}],d={toc:b};function f(e){var n=e.components,t=Object(a.a)(e,l);return Object(o.b)("wrapper",Object(r.a)({},d,t,{components:n,mdxType:"MDXLayout"}),Object(o.b)("p",null,"There are the following options for configuration:"),Object(o.b)("ul",null,Object(o.b)("li",{parentName:"ul"},"Min OS Version"),Object(o.b)("li",{parentName:"ul"},"Banned model"),Object(o.b)("li",{parentName:"ul"},"Banned board"),Object(o.b)("li",{parentName:"ul"},"Banned bootloader"),Object(o.b)("li",{parentName:"ul"},"Banned CPUs"),Object(o.b)("li",{parentName:"ul"},"Banned device"),Object(o.b)("li",{parentName:"ul"},"Banned hardware"),Object(o.b)("li",{parentName:"ul"},"Banned host")),Object(o.b)("h2",{id:"configuration"},"Configuration"),Object(o.b)("p",null,"The gist of the configuration is to build a 'Conditional'. We have two rules"),Object(o.b)("p",null,Object(o.b)("inlineCode",{parentName:"p"},"and")," and ",Object(o.b)("inlineCode",{parentName:"p"},"or")," (",Object(o.b)("inlineCode",{parentName:"p"},"with is the same as and"),")"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'// This adds a rule so that both minOsVersion and notManufacturer\n// are failable rules\nwith(minOsVersion(22))\nand(notManufacturer("Abc"))\n\n// This rule will not fail if the notManufacturer rule passes\n// because if a single \'or\' passes, it doesn\'t matter how many\n// ands fail\n\n// This will fail if the minOsVersion is 22, or if the\n// manufacturer is "Def" unless the manufacturer is NOT\n// "ABC" in which case it\'ll pass every time\n\nwith(minOsVersion(22))\nand(notManufacturer("Def"))\nor(notManufacturer("Abc")\n\n')),Object(o.b)("p",null,"Here are all the checks you can use :"),Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'minOsVersion(123)\nnotManufacturer("")\nbannedBoard("")\nbannedBootloader("")\nbannedCpus("")\nbannedDevice("")\nbannedHardware("")\nbannedHost("")\nbannedModel("")\n')),Object(o.b)(i.a,{defaultValue:"ondevice",values:[{label:"On device",value:"ondevice"},{label:"Off device",value:"offdevice"}],mdxType:"Tabs"},Object(o.b)(c.a,{value:"ondevice",mdxType:"TabItem"},Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'// OS Blacklist version\nosDetectionCheck(\n    conditionalBuilder {\n        with(minOsVersion(22))\n        and(notManufacturer("Abc"))\n    }\n).warn()\n'))),Object(o.b)(c.a,{value:"offdevice",mdxType:"TabItem"},Object(o.b)("pre",null,Object(o.b)("code",{parentName:"pre",className:"language-kotlin"},'\nosDetectionCheck(\n    context,\n    conditionalBuilder {\n        with(minOsVersion(22))\n        and(notManufacturer("Abc"))\n    }\n).error()\n')))))}f.isMDXComponent=!0},116:function(e,n,t){"use strict";t.d(n,"a",(function(){return b})),t.d(n,"b",(function(){return p}));var r=t(0),a=t.n(r);function o(e,n,t){return n in e?Object.defineProperty(e,n,{value:t,enumerable:!0,configurable:!0,writable:!0}):e[n]=t,e}function i(e,n){var t=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);n&&(r=r.filter((function(n){return Object.getOwnPropertyDescriptor(e,n).enumerable}))),t.push.apply(t,r)}return t}function c(e){for(var n=1;n<arguments.length;n++){var t=null!=arguments[n]?arguments[n]:{};n%2?i(Object(t),!0).forEach((function(n){o(e,n,t[n])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(t)):i(Object(t)).forEach((function(n){Object.defineProperty(e,n,Object.getOwnPropertyDescriptor(t,n))}))}return e}function l(e,n){if(null==e)return{};var t,r,a=function(e,n){if(null==e)return{};var t,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||(a[t]=e[t]);return a}(e,n);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var u=a.a.createContext({}),s=function(e){var n=a.a.useContext(u),t=n;return e&&(t="function"==typeof e?e(n):c(c({},n),e)),t},b=function(e){var n=s(e.components);return a.a.createElement(u.Provider,{value:n},e.children)},d={inlineCode:"code",wrapper:function(e){var n=e.children;return a.a.createElement(a.a.Fragment,{},n)}},f=a.a.forwardRef((function(e,n){var t=e.components,r=e.mdxType,o=e.originalType,i=e.parentName,u=l(e,["components","mdxType","originalType","parentName"]),b=s(t),f=r,p=b["".concat(i,".").concat(f)]||b[f]||d[f]||o;return t?a.a.createElement(p,c(c({ref:n},u),{},{components:t})):a.a.createElement(p,c({ref:n},u))}));function p(e,n){var t=arguments,r=n&&n.mdxType;if("string"==typeof e||r){var o=t.length,i=new Array(o);i[0]=f;var c={};for(var l in n)hasOwnProperty.call(n,l)&&(c[l]=n[l]);c.originalType=e,c.mdxType="string"==typeof e?e:r,i[1]=c;for(var u=2;u<o;u++)i[u]=t[u];return a.a.createElement.apply(null,i)}return a.a.createElement.apply(null,t)}f.displayName="MDXCreateElement"},117:function(e,n,t){"use strict";function r(e){var n,t,a="";if("string"==typeof e||"number"==typeof e)a+=e;else if("object"==typeof e)if(Array.isArray(e))for(n=0;n<e.length;n++)e[n]&&(t=r(e[n]))&&(a&&(a+=" "),a+=t);else for(n in e)e[n]&&(a&&(a+=" "),a+=n);return a}n.a=function(){for(var e,n,t=0,a="";t<arguments.length;)(e=arguments[t++])&&(n=r(e))&&(a&&(a+=" "),a+=n);return a}},121:function(e,n,t){"use strict";var r=t(0),a=t(122);n.a=function(){var e=Object(r.useContext)(a.a);if(null==e)throw new Error("`useUserPreferencesContext` is used outside of `Layout` Component.");return e}},122:function(e,n,t){"use strict";var r=t(0),a=Object(r.createContext)(void 0);n.a=a},127:function(e,n,t){"use strict";var r=t(0),a=t.n(r),o=t(121),i=t(117),c=t(55),l=t.n(c);var u=37,s=39;n.a=function(e){var n=e.lazy,t=e.block,c=e.defaultValue,b=e.values,d=e.groupId,f=e.className,p=Object(o.a)(),m=p.tabGroupChoices,O=p.setTabGroupChoices,h=Object(r.useState)(c),v=h[0],g=h[1],j=r.Children.toArray(e.children),y=[];if(null!=d){var w=m[d];null!=w&&w!==v&&b.some((function(e){return e.value===w}))&&g(w)}var k=function(e){var n=e.currentTarget,t=y.indexOf(n),r=b[t].value;g(r),null!=d&&(O(d,r),setTimeout((function(){var e,t,r,a,o,i,c,u;(e=n.getBoundingClientRect(),t=e.top,r=e.left,a=e.bottom,o=e.right,i=window,c=i.innerHeight,u=i.innerWidth,t>=0&&o<=u&&a<=c&&r>=0)||(n.scrollIntoView({block:"center",behavior:"smooth"}),n.classList.add(l.a.tabItemActive),setTimeout((function(){return n.classList.remove(l.a.tabItemActive)}),2e3))}),150))},N=function(e){var n,t;switch(e.keyCode){case s:var r=y.indexOf(e.target)+1;t=y[r]||y[0];break;case u:var a=y.indexOf(e.target)-1;t=y[a]||y[y.length-1]}null===(n=t)||void 0===n||n.focus()};return a.a.createElement("div",{className:"tabs-container"},a.a.createElement("ul",{role:"tablist","aria-orientation":"horizontal",className:Object(i.a)("tabs",{"tabs--block":t},f)},b.map((function(e){var n=e.value,t=e.label;return a.a.createElement("li",{role:"tab",tabIndex:v===n?0:-1,"aria-selected":v===n,className:Object(i.a)("tabs__item",l.a.tabItem,{"tabs__item--active":v===n}),key:n,ref:function(e){return y.push(e)},onKeyDown:N,onFocus:k,onClick:k},t)}))),n?Object(r.cloneElement)(j.filter((function(e){return e.props.value===v}))[0],{className:"margin-vert--md"}):a.a.createElement("div",{className:"margin-vert--md"},j.map((function(e,n){return Object(r.cloneElement)(e,{key:n,hidden:e.props.value!==v})}))))}},128:function(e,n,t){"use strict";var r=t(0),a=t.n(r);n.a=function(e){var n=e.children,t=e.hidden,r=e.className;return a.a.createElement("div",{role:"tabpanel",hidden:t,className:r},n)}}}]);