import React from 'react';
import {withStyles} from "@material-ui/core/styles/index";

import {Link, Route, Redirect} from 'react-router-dom'

import Grid from '@material-ui/core/Grid';

import Paper from '@material-ui/core/Paper';

import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

import MemberGroupFeature from 'flock-eco-feature-member/member_group/MemberGroupFeature'
import MemberFieldFeature from 'flock-eco-feature-member/member_field/MemberFieldFeature'

const styles = {}

class AppSettings extends React.Component {

  render() {
    return (
      <Grid
        container
        spacing={16}
        wrap="nowrap"
      >
        <Grid item style={{width: '250px'}}>
          <Paper>
            <List component="nav">

              <ListItem button>
                <Link to={`/settings/member_groups`}>
                  <ListItemText primary="Member Groups"/>
                </Link>
              </ListItem>

              <ListItem button>
                <Link to={`/settings/member_fields`}>
                  <ListItemText primary="Member Fields"/>
                </Link>
              </ListItem>

              {/*<ListItem button>*/}
              {/*<ListItemText primary="Drafts" />*/}
              {/*</ListItem>*/}

            </List>
          </Paper>
        </Grid>

        <Grid item style={{width: '100%'}}>
          <Paper>

            <Route path='/settings' exact render={(props) => (
              <Redirect to="/settings/member_groups"/>
            )}/>

            <Route path='/settings/member_groups' exact render={(props) => (
              <MemberGroupFeature/>
            )}/>

            <Route path='/settings/member_fields' exact render={(props) => (
              <MemberFieldFeature/>
            )}/>

          </Paper>
        </Grid>
      </Grid>
    )
  }

}

export default withStyles(styles)(AppSettings);