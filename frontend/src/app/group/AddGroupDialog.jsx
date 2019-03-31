import { Dialog, DialogContent, DialogTitle } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import TextField from "@material-ui/core/TextField";
import React, { useState } from 'react';
import css from './group.module.scss'

const defaultPhotoUrl = "https://3ie87c2dond928rt2e2zzo8o-wpengine.netdna-ssl.com/wp-content/themes/gonzo/images/no-image-featured-image.png";

const AddGroupDialog = ({ open, onSubmit, onClose }) => {
    const [name, setName] = useState("");
    const [photoUrl, setPhotoUrl] = useState("");

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
};


export default AddGroupDialog;
