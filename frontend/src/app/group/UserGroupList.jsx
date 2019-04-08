import React from 'react';
import PropTypes from 'prop-types'
import { Link } from "react-router-dom";
import ImageAvatars from "./GroupAvatar";
import css from './group.module.scss';

const UserGroupList = ({ groups }) => (
    <ul>

        {groups.map(group => (
            <div className={css.groupItem}>
            <ImageAvatars/>
            <li key={group.id}>

                <Link to={`/groups/${group.id}`}>
                    {group.name}
                </Link>
            </li>
            </div>

        ))}

    </ul>);


UserGroupList.propTypes = {
    groups: PropTypes.arrayOf(PropTypes.shape({
        name: PropTypes.string,
    })).isRequired
};
export default UserGroupList;
