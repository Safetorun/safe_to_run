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
                Safe to run is a simple to use, tool to help with Android security.
            </>
        ),
    },
    {
        title: 'Off device',
        // imageUrl: 'img/undraw_docusaurus_tree.svg',
        description: (
            <>
                Safe to run can run on and off device - perform your checks where it makes
                sense to you
            </>
        ),
    },
    {
        title: 'Root detection', description: (
            <>
                Detect rooted devices
            </>)
    },
    {
        title: 'Signature check', description: (
            <>
                Verify the signature of your application
            </>)
    },
    {title: 'Blacklisted apps', description: (<>Stop running if known malicious applications are running </>)},
    {title: 'Install origin', description: (<>Enforce how your app is installed)</>)},
    {title: 'Os configuration check', description: (<>enforce min OS versions, avoid certain manufacturers etc</>)},
    {title: 'Debug check', description: (<>Added protection against reverse engineering</>)},
];

const secondRow = [


];

const thirdRow = [

]

function Feature({imageUrl, title, description}) {
    const imgUrl = useBaseUrl(imageUrl);
    return (
        <div className={classnames('col', styles.feature)}>
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

                    {secondRow && secondRow.length > 0 && (
                        <section className={styles.section}>
                            <div className={styles.features}>
                                {secondRow.map((props, idx) => (
                                    <Feature key={idx} {...props} />
                                ))}
                            </div>
                        </section>
                    )}

                    {thirdRow && thirdRow.length > 0 && (
                        <section className={styles.section}>
                            <div className={styles.features}>
                                {thirdRow.map((props, idx) => (
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
