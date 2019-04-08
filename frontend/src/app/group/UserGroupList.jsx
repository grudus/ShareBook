import Avatar from "@material-ui/core/Avatar";
import PropTypes from 'prop-types'
import React from 'react';
import { Link } from "react-router-dom";
import css from './group.module.scss';

const UserGroupList = ({ groups }) => (
    <ul>
        {groups.map(group => (
            <li key={group.id} className={css.groupItem}>
                <Link to={`/groups/${group.id}`}>
                    <div className={css.textAvatarWrapper}>
                        <Avatar alt="Remy Sharp"
                                src={group.photoUrl}
                        className={css.userGroupAvatar}/>
                        {group.name}
                    </div>
                </Link>
            </li>

        ))}

    </ul>);


UserGroupList.propTypes = {
    groups: PropTypes.arrayOf(PropTypes.shape({
        name: PropTypes.string,
    })).isRequired
};
export default UserGroupList;
