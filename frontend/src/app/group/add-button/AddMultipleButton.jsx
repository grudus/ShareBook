import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import React, { useState } from 'react';
import css from "./add-multiple-button.module.scss";


const AddMultipleButton = ({ onAddGroup, onAddPost }) => {
    const [isOpen, setIsOpen] = useState(false);
    const toggleOpen = () => setIsOpen(!isOpen);

    const openDialog = (func) => {
        setIsOpen(false);
        func()
    };

    return (
        <>
            <div className={css.modalBackground + " " + (isOpen ? css.open : '')}
                 onClick={() => setIsOpen(false)}/>
            <div className={css.buttonWrapper}>
                <div className={css.selectOptionsWrapper + " " + (isOpen ? css.open : '')}>
                    <Fab variant={"extended"} className={css.optionToSelect} onClick={() => openDialog(onAddGroup)}>
                        Dodaj grupę
                    </Fab>
                    <Fab variant={"extended"} className={css.optionToSelect} onClick={() => openDialog(onAddPost)}>
                        Dodaj post
                    </Fab>
                </div>

                <Fab
                    color={"primary"} onClick={toggleOpen}>
                    <AddIcon/>
                </Fab>
            </div>
        </>
    );
};


export default AddMultipleButton;
