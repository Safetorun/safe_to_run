import React from 'react';
import classnames from 'classnames';
import Layout from '@theme/Layout';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import useBaseUrl from '@docusaurus/useBaseUrl';

import styles from './styles.module.css';

const features = [
    {
        title: 'Easy to Use',
        // imageUrl: 'img/undraw_docusaurus_mountain.svg',
        description: (
            <>
                Safe to run is a simple to use, tool to help with Android security
            </>
        ),
    },
    {
        title: 'Configurable',
        // imageUrl: 'img/undraw_docusaurus_tree.svg',
        description: (
            <>
                Safe to run is easily configured to suit your apps specific needs
            </>
        ),
    },
    {
        title: 'Extensible',
        // imageUrl: 'img/undraw_docusaurus_tree.svg',
        description: (
            <>
                Safe to run is extensible to add your own security rules
            </>
        ),
    },
];

function Feature({imageUrl, title, description}) {
    const imgUrl = useBaseUrl(imageUrl);
    return (
        <div className={classnames('col col--4', styles.feature)}>
            {imgUrl && (
                <div className="text--center">
                    <img className={styles.featureImage} src={imgUrl} alt={title}/>
                </div>
            )}
            <h3>{title}</h3>
            <p>{description}</p>
        </div>
    );
}

export default function Home() {
    const context = useDocusaurusContext();
    const {siteConfig = {}} = context;

    return (
        <Layout
            title={`${siteConfig.title}`}
            description="Safe to run is a tool for developers to develop secure mobile apps">
            <div className={styles.hero}>
                <header>
                    <h1>{siteConfig.title}</h1>
                    <p>{siteConfig.tagline}</p>
                    <div className={styles.buttons}>
                        <Link to={useBaseUrl('docs/')}>Get Started</Link>
                    </div>
                </header>
                <main>
                    {features && features.length > 0 && (
                        <section className={styles.section}>
                            <div className={styles.features}>
                                {features.map((props, idx) => (
                                    <Feature key={idx} {...props} />
                                ))}
                            </div>
                        </section>
                    )}

                    <h3 className="text--center">Gallery</h3>
                    <section>
                    <div className="text--center">
                        <img className={styles.mainImage} src="img/android_sample.png" alt={siteConfig.title}/>
                    </div>
                    </section>

                </main>
            </div>
        </Layout>
    );
}
