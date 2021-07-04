import React from 'react';
import classnames from 'classnames';
import Layout from '@theme/Layout';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import useBaseUrl from '@docusaurus/useBaseUrl';
import {ReactMailchimpEmailSignupForm} from 'react-mailchimp-email-signup-form';

import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

import styles from './styles.module.css';
import {CardHeader, CardMedia} from "@material-ui/core";

const features = [
    {
        title: 'Easy to Use',
        imageUrl: "img/simple.png",
        description: (
            <>
                Android security is hard, safe to run is a simple to use tool to help protect your app from hackers.
            </>
        ),
    },
    {
        title: 'Check it\'s safe',
        imageUrl: 'img/secure.jpg',
        description: (
            <>
                The premise of safe to run is a simple question - is it safe to run my app or function.
                With everything from root detection, to blacklisted apps and signature verification - safe to
                run gives you confidence
            </>)
    },
    {
        title: 'Robust recompilation protection',
        imageUrl: 'img/robust.jpg',
        description: (
            <>
                Safe to run provides an interface to protect against attackers modifying and recompiling
                your binary
            </>)
    },
];


function Feature({imageUrl, title, description}) {
    const imgUrl = useBaseUrl(imageUrl);
    return (
        <Card>
            <CardHeader title={title}/>
            <CardMedia
                className={styles.featureImage}
                image={imgUrl}
                title={title}
            />
            <CardContent>
                <Typography  color="textSecondary">
                    {description}
                </Typography>
            </CardContent>
        </Card>
    );
}

const SignupForm = () => (
    <ReactMailchimpEmailSignupForm
        elementId="first-email-signup-form"
        url="https://github.us6.list-manage.com/subscribe/post?u=70d44300a9cc26801ede69842&amp;id=8d2000cee2"
        title="Subscribe for Safe to run updates"
        subtitle="We take privacy seriously and we'll never spam or sell your information."
    />
);

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
                    <div className={styles.mailChimpContainer}>
                        {SignupForm()}
                    </div>
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
