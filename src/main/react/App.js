import React from 'react';

import { HashRouter, Route } from 'react-router-dom'

import AppLayout from './AppLayout'

import DonationManager from './donation/DonationManager'
import UserManagement from 'eco-feature-users/UserManager.js'


const theme = {

}


class App extends React.Component {


  render() {

    return (
      <HashRouter>
        <AppLayout theme={theme}>

          <Route path='/' exact render={(props) => (
            <h1>Welcome</h1>
          )}/>

          <Route path='/donations' exact render={(props) => (
            <DonationManager/>
          )}/>


          <Route path='/users' exact render={(props) => (
            <UserManagement/>
          )}/>

        </AppLayout>
      </HashRouter>
    )
  }
}

export default App