import React from 'react';

import {HashRouter, Route} from 'react-router-dom'

import AppLayout from './AppLayout'

import DonationManager from '../donation/DonationManager'
import MemberManager from 'eco-feature-members/MemberManager'
import UserManagement from 'eco-feature-users/UserManager'

import {createMuiTheme} from '@material-ui/core/styles';

import purple from '@material-ui/core/colors/purple';
import orange from '@material-ui/core/colors/orange';

class App extends React.Component {

  get theme() {
    return createMuiTheme({
      palette: {
        primary: purple,
        secondary: {
          main: '#f44336',
        },
      },
    })
  }

  render() {

    return (
      <HashRouter>
        <AppLayout theme={this.theme}>

          <Route path='/' exact render={(props) => (
            <h1>Welcome</h1>
          )}/>

          <Route path='/members' exact render={(props) => (
            <MemberManager/>
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