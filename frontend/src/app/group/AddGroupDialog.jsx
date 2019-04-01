import { Dialog, DialogContent, DialogTitle } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import TextField from "@material-ui/core/TextField";
import React, {Component, useState} from 'react';
import css from './group.module.scss'
import * as PropTypes from "prop-types";

const defaultPhotoUrl = "https://3ie87c2dond928rt2e2zzo8o-wpengine.netdna-ssl.com/wp-content/themes/gonzo/images/no-image-featured-image.png";

class AddGroupDialog extends Component {

    state = {
        name: "",
        photoUrl: null,
    }

    render() {
        let {open, onSubmit, onClose} = this.props;
        const {name, photoUrl} = this.state;
        const setName = name => this.setState({name});
        const setPhotoUrl = photoUrl => this.setState({photoUrl});

        return (

            <Dialog open={open}
                    onClose={onClose}
                    fullWidth
                    maxWidth="sm">
                <DialogTitle>Dodaj grupę</DialogTitle>
                <DialogContent>
                    <img src={photoUrl || defaultPhotoUrl} className={css.photoImg}/>

                    <TextField
                        autoFocus
                        margin="dense"
                        id="add-grup-name"
                        label="Nazwa grupy"
                        type="text"
                        fullWidth
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <TextField
                        margin="dense"
                        id="add-grup-photo"
                        label="Adres zdjęcia"
                        type="text"
                        fullWidth
                        value={photoUrl}
                        onChange={e => setPhotoUrl(e.target.value)}
                    />
                </DialogContent>

                <DialogActions>
                    <Button onClick={onClose} color="primary" variant="text">
                        Cofnij
                    </Button>
                    <Button onClick={() => {
                        onSubmit(name, photoUrl);
                        setName("");
                        setPhotoUrl("");
                        onClose();
                    }} color="primary" variant="contained">
                        Dodaj
                    </Button>
                </DialogActions>
            </Dialog>

        );
    }
}

AddGroupDialog.propTypes = {
    open: PropTypes.any,
    onSubmit: PropTypes.any,
    onClose: PropTypes.any
}


export default AddGroupDialog;
