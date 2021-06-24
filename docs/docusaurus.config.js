/** @type {import('@docusaurus/types').DocusaurusConfig} */
module.exports = {
    title: 'Safe to run',
    tagline: 'Secure your mobile apps',
    url: 'https://safetorun.github.io/safe_to_run',
    baseUrl: '/safe_to_run/',
    onBrokenLinks: 'throw',
    onBrokenMarkdownLinks: 'warn',
    favicon: 'img/favicon.ico',
    organizationName: 'safetorun',
    projectName: 'safe_to_run',
    themeConfig: {
        googleAnalytics: {
            trackingID: 'UA-158023887-2',
            anonymizeIP: true,
        },
        gtag: {
            trackingID: 'UA-158023887-2',
            anonymizeIP: true
        },
        navbar: {
            title: 'Safe to run',
            logo: {
                alt: 'Safe to run',
                src: 'img/safetorun.jpg',
            },
            items: [
                {
                    to: 'docs/',
                    activeBasePath: 'docs',
                    label: 'Docs',
                    position: 'left',
                },
                {to: 'blog', label: 'Blog', position: 'left'},
                {
                    href: 'https://github.com/safetorun/safe_to_run/',
                    label: 'GitHub',
                    position: 'right',
                },
            ],
        },
        footer: {
            style: 'dark',
            links: [],
            copyright: `Copyright © ${new Date().getFullYear()} Daniel Llewellyn, Inc. Built with Docusaurus.`,
        },
        prism: {
            additionalLanguages: ['kotlin', 'groovy'],
        }
    },
    presets: [
        [
            '@docusaurus/preset-classic',
            {
                docs: {
                    sidebarPath: require.resolve('./sidebars.js'),
                    editUrl:
                        'https://github.com/safetorun/safe_to_run/edit/master/docs/',
                },
            },
        ],
    ],
};
