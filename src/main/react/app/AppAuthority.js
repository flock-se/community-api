import React from 'react';

const AppAuthority = fetch('/api/users/me')
  .then(res => res.json())
  .then(user => user.principal.authorities)
  .then(authorities => {
    return {
      hasAuthority: (authority) => {
        return authorities.includes(authority)
      }
    }
  })

export default AppAuthority