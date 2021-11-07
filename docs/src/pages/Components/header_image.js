import Link from '@docusaurus/Link';
import Button from '@material-ui/core/Button'
import { CardHeader, CardMedia, Grid } from "@material-ui/core";

export default HeaderImage = () => (
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
                <Box> <Button sx={{ p: 4 }} color="primary" variant="contained">Get started</Button></Box>
            </Link>
        </div>
    </div>
);