import Button from '@material-ui/core/Button/index';
import ClickAwayListener from '@material-ui/core/ClickAwayListener/index';
import Grow from '@material-ui/core/Grow/index';
import MenuItem from '@material-ui/core/MenuItem/index';
import MenuList from '@material-ui/core/MenuList/index';
import Paper from '@material-ui/core/Paper/index';
import Popper from '@material-ui/core/Popper/index';
import { withStyles } from '@material-ui/core/styles/index';
import PropTypes from 'prop-types';
import React from 'react';
import { Link, withRouter } from "react-router-dom";
import * as AuthApi from '../../auth/AuthApi'
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

    handleToggle = () => {
        this.setState(state => ({ open: !state.open }));
    };

    handleClose = event => {
        if (this.anchorEl.contains(event.target)) {
            return;
        }

        this.setState({ open: false });
    };

    attach = async (event) => {
        await AuthApi.logout();
        this.handleClose(event);
        this.props.history.push("/auth/login");
    };

    render() {
        const { open } = this.state;
        const { userName } = this.props;

        return (
            <div>
                <div>
                    <AttachFile
                        buttonRef={node => {
                            this.anchorEl = node;
                        }}
                        aria-owns={open ? 'attach' : undefined}
                        aria-haspopup="true"
                        onClick={this.handleToggle}
                    >
                        {userName}
                    </AttachFile>
                    <Popper open={open} anchorEl={this.anchorEl} transition disablePortal>
                        {({ TransitionProps, placement }) => (
                            <Grow
                                {...TransitionProps}
                                id="attach"
                                style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
                            >
                                <Paper>
                                    <ClickAwayListener onClickAway={this.handleClose}>
                                        <MenuList>
                                            <MenuItem onClick={this.logout}>Załącz plik</MenuItem>
                                        </MenuList>
                                    </ClickAwayListener>
                                </Paper>
                            </Grow>
                        )}
                    </Popper>
                </div>
            </div>
        );
    }
}

Attachments.propTypes = {
    userName: PropTypes.string.isRequired,
};

export default withStyles(styles)(withRouter(Attachments));