export const saveCurrentUser = (user) => {
    localStorage.setItem("USER", JSON.stringify(user));
};

export const getCurrentUser = () => JSON.parse(localStorage.getItem("USER") || {});
