import { Dialog, DialogContent, DialogTitle } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import TextField from "@material-ui/core/TextField";
import * as PropTypes from "prop-types";
import React, { Component } from 'react';
import Attachments from "../../group/attachment/Attachments"
import css from './dialogs.module.scss'
import CommentForm from "../posts/single-post/SinglePost";
import {addAttachment} from "../attachment/AttachApi";
import { FilePond } from 'react-filepond';
import 'filepond/dist/filepond.min.css';
import {BACKEND_URL} from "../../env/environment";


class AddPostDialog extends Component {

    state = {
        postText: '',
        file: null
    };

    submitPost = (e) => {
        if (e && e.preventDefault)
            e.preventDefault();
        this.props.onSubmit(this.state.postText);
        this.setState({ postText: '' });
        this.props.onClose();
    };

    render() {
        let { open, onClose } = this.props;
        const updateText = ({ target }) => {
            return this.setState({ postText: target.value });
        };

        return (

            <Dialog open={open}
                    onClose={onClose}
                    fullWidth
                    maxWidth="sm">
                <DialogTitle>Dodaj wpis</DialogTitle>
                <DialogContent>
                    <form onSubmit={this.submitPost}>
                        <div>
                        <div className={css.postName}>
                        <TextField
                            autoFocus
                            margin="dense"
                            type="text"
                            placeholder="Napisz coś..."
                            fullWidth
                            value={this.state.postText}
                            onChange={updateText}
                        />
                        </div>
                        <div className={css.files}>
                        <FilePond server={`${BACKEND_URL}/attachments`}/>
                        </div>
                        </div>
                    </form>
                </DialogContent>

                <DialogActions>
                    <Button onClick={onClose} color="primary" variant="text">
                        Cofnij
                    </Button>
                    <Button onClick={this.submitPost} color="primary" variant="contained">
                        Dodaj
                    </Button>
                </DialogActions>
            </Dialog>

        );
    }

}

AddPostDialog.propTypes = {
    open: PropTypes.any,
    onSubmit: PropTypes.any,
    onClose: PropTypes.any
};


export default AddPostDialog;
