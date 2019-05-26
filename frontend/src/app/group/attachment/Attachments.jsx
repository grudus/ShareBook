import { withStyles } from '@material-ui/core/styles/index';
import React from 'react';
import { Link, withRouter } from "react-router-dom";
import AttachFile from "@material-ui/icons/AttachFile";
import * as PropTypes from "prop-types";
import CommentForm from "../comment/CommentForm";


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
        file: '',
    };

    addAttachment = (e) => {
        if (e && e.preventDefault)
            e.preventDefault();
        this.props.actionWhenAddAttachment(this.state.file);
        this.setState({ file: '' });
    };

    render() {

        return (
            <div>
                <div>
                    <AttachFile
                        onClick={this.addAttachment}
                        value={this.state.file}
                    >
                    </AttachFile>
                </div>
            </div>
        );
    }
}

Attachments.propTypes = {
    actionWhenAddAttachment: PropTypes.any,
};

export default withStyles(styles)(withRouter(Attachments));