package com.example.mormeehing;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private BottomNavigationView bottomAppBar;
    private FloatingActionButton createJobFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);
        createJobFab = findViewById(R.id.create_job_fab);
        ViewCompat.setOnApplyWindowInsetsListener(bottomAppBar, (view, insets) -> {
            view.setPadding(0, 0, 0, 0);
            return insets;
        });

        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host);
        if (navHost == null) {
            throw new IllegalStateException("Navigation host is missing");
        }
        navController = navHost.getNavController();

        bottomAppBar.setOnItemSelectedListener(this::onBottomMenuItemSelected);
        createJobFab.setOnClickListener(view -> navigateTo(R.id.createJobFragment));
        navController.addOnDestinationChangedListener(this::onDestinationChanged);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets navigationBars = insets.getInsetsIgnoringVisibility(
                    WindowInsetsCompat.Type.navigationBars());
            int bottomInset = Math.max(systemBars.bottom, navigationBars.bottom);
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            CoordinatorLayout.LayoutParams bottomBarParams =
                    (CoordinatorLayout.LayoutParams) bottomAppBar.getLayoutParams();
            bottomBarParams.bottomMargin = bottomInset;
            bottomAppBar.setLayoutParams(bottomBarParams);
            return insets;
        });
    }

    private boolean onBottomMenuItemSelected(MenuItem item) {
        int destinationId = destinationForMenuItem(item.getItemId());
        if (destinationId == 0) {
            return false;
        }
        navigateTo(destinationId);
        return true;
    }

    private void navigateTo(@IdRes int destinationId) {
        if (navController.getCurrentDestination() == null
                || navController.getCurrentDestination().getId() == destinationId) {
            return;
        }
        NavOptions options = new NavOptions.Builder()
                .setLaunchSingleTop(true)
                .build();
        navController.navigate(destinationId, null, options);
    }

    private void onDestinationChanged(
            NavController controller,
            NavDestination destination,
            Bundle arguments) {
        boolean showBottomMenu = destination.getId() == R.id.homeFragment
                || destination.getId() == R.id.searchFragment
                || destination.getId() == R.id.createJobFragment
                || destination.getId() == R.id.scheduleFragment
                || destination.getId() == R.id.profileFragment
                || destination.getId() == R.id.workHoursFragment
                || destination.getId() == R.id.incomeFragment
                || destination.getId() == R.id.applicationsFragment;

        bottomAppBar.setVisibility(showBottomMenu ? View.VISIBLE : View.GONE);
        createJobFab.setVisibility(showBottomMenu ? View.VISIBLE : View.GONE);

        int selectedMenuId = menuItemForDestination(destination.getId());
        if (selectedMenuId != 0) {
            MenuItem selectedItem = bottomAppBar.getMenu().findItem(selectedMenuId);
            if (selectedItem != null) {
                selectedItem.setChecked(true);
            }
        }
    }

    private int destinationForMenuItem(int menuItemId) {
        if (menuItemId == R.id.nav_home) {
            return R.id.homeFragment;
        }
        if (menuItemId == R.id.nav_search) {
            return R.id.searchFragment;
        }
        if (menuItemId == R.id.nav_schedule) {
            return R.id.scheduleFragment;
        }
        if (menuItemId == R.id.nav_profile) {
            return R.id.profileFragment;
        }
        return 0;
    }

    private int menuItemForDestination(int destinationId) {
        if (destinationId == R.id.homeFragment) {
            return R.id.nav_home;
        }
        if (destinationId == R.id.searchFragment) {
            return R.id.nav_search;
        }
        if (destinationId == R.id.scheduleFragment) {
            return R.id.nav_schedule;
        }
        if (destinationId == R.id.profileFragment) {
            return R.id.nav_profile;
        }
        return 0;
    }
}
