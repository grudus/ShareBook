import React from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button/index';
import ClickAwayListener from '@material-ui/core/ClickAwayListener/index';
import Grow from '@material-ui/core/Grow/index';
import Paper from '@material-ui/core/Paper/index';
import Popper from '@material-ui/core/Popper/index';
import MenuItem from '@material-ui/core/MenuItem/index';
import MenuList from '@material-ui/core/MenuList/index';
import { withStyles } from '@material-ui/core/styles/index';
import { withRouter } from "react-router-dom";
import css from '../group/group.module.scss';
import * as AuthApi from '../auth/AuthApi'

const styles = theme => ({
    root: {
        display: 'flex',
    },
    paper: {
        marginRight: theme.spacing.unit * 2,
    },
});

class MenuListComposition extends React.Component {
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

    logout = async (event) => {
        await AuthApi.logout();
        this.handleClose(event);
        this.props.history.push("/auth/login");
    };

    render() {
        const { classes } = this.props;
        const { open } = this.state;

        return (
            <div className={css.Menu}>
                <div>
                    <Button
                        className={css.menuButton}
                        buttonRef={node => {
                            this.anchorEl = node;
                        }}
                        aria-owns={open ? 'menu-list-grow' : undefined}
                        aria-haspopup="true"
                        onClick={this.handleToggle}
                    >
                        Admin
                    </Button>
                    <Popper open={open} anchorEl={this.anchorEl} transition disablePortal>
                        {({ TransitionProps, placement }) => (
                            <Grow
                                {...TransitionProps}
                                id="menu-list-grow"
                                style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
                            >
                                <Paper>
                                    <ClickAwayListener onClickAway={this.handleClose}>
                                        <MenuList>
                                            <MenuItem onClick={this.handleClose}>My account</MenuItem>
                                            <MenuItem onClick={this.handleClose}>My groups</MenuItem>
                                            <MenuItem onClick={this.logout}>Logout</MenuItem>
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

MenuListComposition.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(withRouter(MenuListComposition));
