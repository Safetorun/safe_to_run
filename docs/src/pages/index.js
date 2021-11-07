import React from 'react';
import Layout from '@theme/Layout';

import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import useBaseUrl from '@docusaurus/useBaseUrl';
import { ReactMailchimpEmailSignupForm } from 'react-mailchimp-email-signup-form';
import { ThemeProvider } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import styles from './styles.module.css';
import { Box, CardMedia, Grid } from "@material-ui/core";
import { createTheme } from '@material-ui/core/styles'
import Link from '@docusaurus/Link';
import Button from '@material-ui/core/Button'
import Microlink from '@microlink/react'



const theme = createTheme({
    palette: {
        primary: { 500: '#467fcf' },
    },
});

const mediumArticles = [
    'https://danielllewellyn.medium.com/secure-against-urls-attacks-on-android-6cffc19b62a5',
    'https://danielllewellyn.medium.com/android-attacks-information-leakage-from-file-intents-3c4f8ac9dc7',
    'https://danielllewellyn.medium.com/securing-android-apps-with-safe-to-run-32852729f0d7'
];

const HeaderImage = () => (
    <div className={styles.securityHeader}>
        <div className={styles.headerText}>
            <Grid container>
                <Grid xs={6}></Grid>
                <Grid xs={5}>
                    <h1 className={styles.headerText}>
                        Android security is hard
                    </h1>
                    <p className={styles.headerSubtitle}>
                        Safe to run makes it easy
                    </p>
                </Grid>
                <Grid xs={1}></Grid>
            </Grid>
            <Link to={useBaseUrl('docs/')}>
                <Button sx={{ p: 4 }} color="primary" variant="contained">Get started</Button>
            </Link>
        </div>
    </div>
);

const features = [
    {
        title: 'Easy to Use',
        imageUrl: "img/simple.png",
        description: (
            <>
                Safe to run makes it simple to protect against the most common android vulnerabilities, from
                checking for malicous URLs to preventing unsafe file access
            </>
        ),
    },
    {
        title: 'Check it\'s safe',
        imageUrl: 'img/secure.jpg',
        description: (
            <>
                The premise of safe to run is a simple question - is it safe to run my function.
                We focus on the device and user input.
            </>)
    },
    {
        title: 'Robust recompilation protection',
        imageUrl: 'img/robust.jpg',
        description: (
            <>
                Safe to run provides an interface to protect against attackers modifying and recompiling
                your binary to slow down attackers.
            </>)
    },
];




function Feature({ imageUrl, title, description }) {
    const imgUrl = useBaseUrl(imageUrl);
    return (
        <Grid item xs={12} sm={4} >
            <Card>
                <CardMedia
                    className={styles.featureImage}
                    image={imgUrl}
                    title={title}
                />
                <CardContent>
                    <Typography variant="h6">
                        {title}
                    </Typography>
                    <Typography color="textSecondary">
                        {description}
                    </Typography>
                </CardContent>
            </Card>
        </Grid>
    );
}

const Features = () => (
    features && features.length > 0 && (
        <section className={styles.section}>
            <Box sx={{ m: 4 }}>
                <h2>Features</h2>
                <Grid container spacing={3}>
                    {features.map((props, idx) => (
                        <Feature key={idx} {...props} />
                    ))}
                </Grid>
            </Box>
        </section>
    )
);

const Articles = () => (
    <section>
        <Box sx={{ m: 4 }}>
            <h2>How safe to run can make your app more secure</h2>
            <Grid container>
                {mediumArticles.map((link) => (<Grid item xs={4}>
                    <Box sx={{ p: 2 }}>
                        <Microlink size="large" url={link} />
                    </Box>
                </Grid>))}
            </Grid>
        </Box>
    </section>
);

export default function Home() {
    const context = useDocusaurusContext();
    const { siteConfig = {} } = context;

    return (
        <ThemeProvider theme={theme}>
            <Layout
                title={`${siteConfig.title}`}
                description="Safe to run is a tool for developers to develop secure mobile apps">
                <div className={styles.hero}>
                    <HeaderImage />
                    <Features />
                    <Articles />
                </div>
            </Layout>
        </ThemeProvider>
    );
}
