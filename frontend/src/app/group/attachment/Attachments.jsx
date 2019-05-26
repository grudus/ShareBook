import { withStyles } from '@material-ui/core/styles/index';
import React from 'react';
import { Link, withRouter } from "react-router-dom";
import AttachFile from "@material-ui/icons/AttachFile";


const styles = theme => ({
    root: {
        display: 'flex',
    },
    paper: {
        marginRight: theme.spacing.unit * 2,
    },
});

class Attachments extends React.Component {
    state = {
        open: false,
    };

    render() {
        const { open } = this.state;

        return (
            <div>
                <div>
                    <AttachFile
                        buttonRef={node => {
                            this.anchorEl = node;
                        }}
                        aria-owns={open ? 'attach' : undefined}
                        aria-haspopup="true"
                    >
                    </AttachFile>
                </div>
            </div>
        );
    }
}


export default withStyles(styles)(withRouter(Attachments));