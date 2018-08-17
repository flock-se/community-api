import React from 'react';

const authorities = fetch('/api/users/me')
  .then(res => res.json())
  .then(user => user.authorities)

const AppAuthority = {
  hasAuthority: (authority) => {
    console.log('check', authority)
    return authorities
      .then(it => {
        console.log('check', it)
        return it.includes(authority)
      })

  }
}

export default AppAuthority