import orange from '@material-ui/core/colors/orange';
import indigo from "@material-ui/core/es/colors/indigo";
import { createMuiTheme } from '@material-ui/core/styles';

export default () => createMuiTheme({
    typography: {
        useNextVariants: true,
        h4: {
            fontWeight: 300,
        },
        h5: {
            fontWeight: 300,
            color: '#424242',
            fontSize: '.9rem'
        },
    },
    palette: {
        primary: indigo,
        secondary: orange,
        background: {
            default: '#fafafa',
            paper: '#fff',
        },
    },
});
